<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">患者编号</label>
        <el-input v-model="query.subjid" clearable placeholder="患者编号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">合并用药（是/否）</label>
        <el-input v-model="query.cm3yn" clearable placeholder="合并用药（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">药物名称</label>
        <el-input v-model="query.cm3trt" clearable placeholder="药物名称" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
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
          <el-form-item label="合并用药（是/否）">
            <el-radio v-model="form.cm3yn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="药物名称">
            <el-input v-model="form.cm3trt" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="开始日期">
            <el-date-picker v-model="form.cm3stdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="结束日期">
            <el-date-picker v-model="form.cm3endat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="用药目的">
            <el-input v-model="form.cm3ord" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="剂量">
            <el-input v-model="form.cm3dose" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="给药方式">
            <el-input v-model="form.cm3route" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="form.cm3oth" style="width: 370px;" />
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
        <el-table-column prop="cm3yn" label="合并用药（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.cm3yn] }}
          </template>
        </el-table-column>
        <el-table-column prop="cm3trt" label="药物名称" />
        <el-table-column prop="cm3stdat" label="开始日期" />
        <el-table-column prop="cm3endat" label="结束日期" />
        <el-table-column prop="cm3ord" label="用药目的" />
        <el-table-column prop="cm3dose" label="剂量" />
        <el-table-column prop="cm3route" label="给药方式" />
        <el-table-column prop="cm3oth" label="备注" />
        <el-table-column v-if="checkPer(['admin','combinedmedication:edit','combinedmedication:del'])" label="操作" width="150px" align="center">
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
import crudCombinedmedication from '@/api/combinedmedication'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { columnId: null, subjid: null, cm3yn: null, cm3trt: null, cm3stdat: null, cm3endat: null, cm3ord: null, cm3dose: null, cm3route: null, cm3oth: null }
export default {
  name: 'Combinedmedication',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['yes_no'],
  cruds() {
    return CRUD({ title: '合并用药', url: 'api/combinedmedication', idField: 'columnId', sort: 'columnId,desc', crudMethod: { ...crudCombinedmedication }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'combinedmedication:add'],
        edit: ['admin', 'combinedmedication:edit'],
        del: ['admin', 'combinedmedication:del']
      },
      rules: {
      },
      queryTypeOptions: [
        { key: 'subjid', display_name: '患者编号' },
        { key: 'cm3yn', display_name: '合并用药（是/否）' },
        { key: 'cm3trt', display_name: '药物名称' }
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
