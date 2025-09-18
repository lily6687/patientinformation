<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">患者编号</label>
        <el-input v-model="query.subjid" clearable placeholder="患者编号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">心电图（是/否）</label>
        <el-input v-model="query.egyn" clearable placeholder="心电图（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">检查日期（年/月/日）</label>
        <el-input v-model="query.egchkdat" clearable placeholder="检查日期（年/月/日）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
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
          <el-form-item label="心电图（是/否）">
            <el-radio v-model="form.egyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="检查日期（年/月/日）">
            <el-date-picker v-model="form.egchkdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="检查结果（正常/异常）">
            <el-input v-model="form.egres" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="form.egoth" style="width: 370px;" />
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
        <el-table-column prop="egyn" label="心电图（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.egyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="egchkdat" label="检查日期（年/月/日）" />
        <el-table-column prop="egres" label="检查结果（正常/异常）" />
        <el-table-column prop="egoth" label="备注" />
        <el-table-column v-if="checkPer(['admin','ecgexam:edit','ecgexam:del'])" label="操作" width="150px" align="center">
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
import crudEcgexam from '@/api/ecgexam'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { columnId: null, subjid: null, egyn: null, egchkdat: null, egres: null, egoth: null }
export default {
  name: 'Ecgexam',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['yes_no'],
  cruds() {
    return CRUD({ title: '心电图', url: 'api/ecgexam', idField: 'columnId', sort: 'columnId,desc', crudMethod: { ...crudEcgexam }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'ecgexam:add'],
        edit: ['admin', 'ecgexam:edit'],
        del: ['admin', 'ecgexam:del']
      },
      rules: {
      },
      queryTypeOptions: [
        { key: 'subjid', display_name: '患者编号' },
        { key: 'egyn', display_name: '心电图（是/否）' },
        { key: 'egchkdat', display_name: '检查日期（年/月/日）' }
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
