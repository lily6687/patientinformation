<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">患者编号</label>
        <el-input v-model="query.subjid" clearable placeholder="患者编号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">是否使用其他药物（是/否）</label>
        <el-input v-model="query.cm2yn" clearable placeholder="是否使用其他药物（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">西药药物类型</label>
        <el-input v-model="query.cm2cat" clearable placeholder="西药药物类型" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
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
          <el-form-item label="是否使用其他药物（是/否）">
            <el-radio v-model="form.cm2yn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="西药药物类型">
            <el-input v-model="form.cm2cat" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="退烧药">
            <el-input v-model="form.cm2feb" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="抗病毒药">
            <el-input v-model="form.cm2anvi" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="抗生素">
            <el-input v-model="form.cm2anbio" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="常规补液治疗">
            <el-input v-model="form.ce2reflu" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="激素">
            <el-input v-model="form.cm2hormo" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="其他药物">
            <el-input v-model="form.ce2other" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="请描述">
            <el-input v-model="form.cm2odesc" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="form.cm2oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="中医药名称">
            <el-input v-model="form.cm3trt" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="其他_请注明：">
            <el-input v-model="form.cm3oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="是否有更改治疗方案">
            <el-input v-model="form.cmchyn" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="是_更改时间：_____">
            <el-date-picker v-model="form.cmchdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="是_请描述更改后治疗方案：">
            <el-input v-model="form.cmchoth" style="width: 370px;" />
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
        <el-table-column prop="cm2yn" label="是否使用其他药物（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.cm2yn] }}
          </template>
        </el-table-column>
        <el-table-column prop="cm2cat" label="西药药物类型" />
        <el-table-column prop="cm2feb" label="退烧药" />
        <el-table-column prop="cm2anvi" label="抗病毒药" />
        <el-table-column prop="cm2anbio" label="抗生素" />
        <el-table-column prop="ce2reflu" label="常规补液治疗" />
        <el-table-column prop="cm2hormo" label="激素" />
        <el-table-column prop="ce2other" label="其他药物" />
        <el-table-column prop="cm2odesc" label="请描述" />
        <el-table-column prop="cm2oth" label="备注" />
        <el-table-column prop="cm3trt" label="中医药名称" />
        <el-table-column prop="cm3oth" label="其他_请注明：" />
        <el-table-column prop="cmchyn" label="是否有更改治疗方案" />
        <el-table-column prop="cmchdat" label="是_更改时间：_____" />
        <el-table-column prop="cmchoth" label="是_请描述更改后治疗方案：" />
        <el-table-column v-if="checkPer(['admin','treatmentothermedication:edit','treatmentothermedication:del'])" label="操作" width="150px" align="center">
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
import crudTreatmentothermedication from '@/api/treatmentothermedication'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { columnId: null, subjid: null, cm2yn: null, cm2cat: null, cm2feb: null, cm2anvi: null, cm2anbio: null, ce2reflu: null, cm2hormo: null, ce2other: null, cm2odesc: null, cm2oth: null, cm3trt: null, cm3oth: null, cmchyn: null, cmchdat: null, cmchoth: null }
export default {
  name: 'Treatmentothermedication',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['yes_no'],
  cruds() {
    return CRUD({ title: '治疗情况_其他药物', url: 'api/treatmentothermedication', idField: 'columnId', sort: 'columnId,desc', crudMethod: { ...crudTreatmentothermedication }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'treatmentothermedication:add'],
        edit: ['admin', 'treatmentothermedication:edit'],
        del: ['admin', 'treatmentothermedication:del']
      },
      rules: {
      },
      queryTypeOptions: [
        { key: 'subjid', display_name: '患者编号' },
        { key: 'cm2yn', display_name: '是否使用其他药物（是/否）' },
        { key: 'cm2cat', display_name: '西药药物类型' }
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
