<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">患者编号</label>
        <el-input v-model="query.subjid" clearable placeholder="患者编号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">既往病史（是/否）</label>
        <el-input v-model="query.mhyn" clearable placeholder="既往病史（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">过敏史（是/否）</label>
        <el-input v-model="query.ahyn" clearable placeholder="过敏史（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">合并疾病（是/否）</label>
        <el-input v-model="query.cm1yn" clearable placeholder="合并疾病（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">从不吸烟/已经戒烟/现在吸烟</label>
        <el-input v-model="query.mhsmokyn" clearable placeholder="从不吸烟/已经戒烟/现在吸烟" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <rrOperation :crud="crud" />
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="患者编号">
            <el-input v-model="form.subjid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="诊断">
            <el-input v-model="form.diag" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="既往病史（是/否）">
            <el-radio v-model="form.mhyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="症状/疾病名称">
            <el-input v-model="form.mhnam" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="过敏史（是/否）">
            <el-radio v-model="form.ahyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="过敏原">
            <el-input v-model="form.ahalrgn" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="合并疾病（是/否）">
            <el-radio v-model="form.cm1yn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="合并症名称">
            <el-input v-model="form.cm1trt" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="从不吸烟/已经戒烟/现在吸烟">
            <el-select v-model="form.mhsmokyn" filterable placeholder="请选择">
              <el-option
                v-for="item in dict.smoke_history"
                :key="item.id"
                :label="item.label"
                :value="item.value" />
            </el-select>
          </el-form-item>
          <el-form-item label="戒烟_____年">
            <el-input v-model="form.mhqsmdur" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="吸烟_____年">
            <el-input v-model="form.mhsmkdur" style="width: 370px;" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="subjid" label="患者编号" />
        <el-table-column prop="diag" label="诊断" />
        <el-table-column prop="mhyn" label="既往病史（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.mhyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="mhnam" label="症状/疾病名称" />
        <el-table-column prop="ahyn" label="过敏史（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.ahyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="ahalrgn" label="过敏原" />
        <el-table-column prop="cm1yn" label="合并疾病（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.cm1yn] }}
          </template>
        </el-table-column>
        <el-table-column prop="cm1trt" label="合并症名称" />
        <el-table-column prop="mhsmokyn" label="从不吸烟/已经戒烟/现在吸烟">
          <template slot-scope="scope">
            {{ dict.label.smoke_history[scope.row.mhsmokyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="mhqsmdur" label="戒烟_____年" />
        <el-table-column prop="mhsmkdur" label="吸烟_____年" />
        <el-table-column v-if="checkPer(['admin','historyallergy:edit','historyallergy:del'])" label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import crudHistoryallergy from '@/api/historyallergy'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { columnId: null, subjid: null, diag: null, mhyn: null, mhnam: null, ahyn: null, ahalrgn: null, cm1yn: null, cm1trt: null, mhsmokyn: null, mhqsmdur: null, mhsmkdur: null }
export default {
  name: 'Historyallergy',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['yes_no', 'smoke_history'],
  cruds() {
    return CRUD({ title: '过敏史', url: 'api/historyallergy', idField: 'columnId', sort: 'columnId,desc', crudMethod: { ...crudHistoryallergy }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'historyallergy:add'],
        edit: ['admin', 'historyallergy:edit'],
        del: ['admin', 'historyallergy:del']
      },
      rules: {
      },
      queryTypeOptions: [
        { key: 'subjid', display_name: '患者编号' },
        { key: 'mhyn', display_name: '既往病史（是/否）' },
        { key: 'ahyn', display_name: '过敏史（是/否）' },
        { key: 'cm1yn', display_name: '合并疾病（是/否）' },
        { key: 'mhsmokyn', display_name: '从不吸烟/已经戒烟/现在吸烟' }
      ]
    }
  },
  methods: {
    // 钩子：在获取表格数据之前执行，false 则代表不获取数据
    [CRUD.HOOK.beforeRefresh]() {
      return true
    }
  }
}
</script>

<style scoped>

</style>
