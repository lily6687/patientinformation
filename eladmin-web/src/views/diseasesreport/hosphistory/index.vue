<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">患者编号</label>
        <el-input v-model="query.subjid" clearable placeholder="患者编号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">出院日期（年/月/日）</label>
        <el-input v-model="query.discdat" clearable placeholder="出院日期（年/月/日）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <rrOperation :crud="crud" />
      </div>
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="患者编号" prop="subjid">
            <el-input v-model="form.subjid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="发病日期（年/月/日）">
            <el-date-picker v-model="form.nastdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="就诊日期（年/月/日）">
            <el-date-picker v-model="form.nadocdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="入院日期（年/月/日）">
            <el-date-picker v-model="form.nahosdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="出院记录（是/否）">
            <el-radio v-model="form.discyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="出院日期（年/月/日）">
            <el-date-picker v-model="form.discdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="主诉">
            <el-input v-model="form.cc" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="现病史">
            <el-input v-model="form.hpi" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="诊疗过程描述">
            <el-input v-model="form.trtdesc" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="出院情况">
            <el-input v-model="form.discsupp" style="width: 370px;" />
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
        <el-table-column prop="nastdat" label="发病日期（年/月/日）" />
        <el-table-column prop="nadocdat" label="就诊日期（年/月/日）" />
        <el-table-column prop="nahosdat" label="入院日期（年/月/日）" />
        <el-table-column prop="discyn" label="出院记录（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.discyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="discdat" label="出院日期（年/月/日）" />
        <el-table-column prop="cc" label="主诉" />
        <el-table-column prop="hpi" label="现病史" />
        <el-table-column prop="trtdesc" label="诊疗过程描述" />
        <el-table-column prop="discsupp" label="出院情况" />
        <el-table-column v-if="checkPer(['admin','hosphistory:edit','hosphistory:del'])" label="操作" width="150px" align="center">
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
import crudHosphistory from '@/api/hosphistory'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { columnId: null, subjid: null, nastdat: null, nadocdat: null, nahosdat: null, discyn: null, discdat: null, cc: null, hpi: null, trtdesc: null, discsupp: null }
export default {
  name: 'Hosphistory',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['yes_no'],
  cruds() {
    return CRUD({ title: '发病史', url: 'api/hosphistory', idField: 'columnId', sort: 'columnId,desc', crudMethod: { ...crudHosphistory }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'hosphistory:add'],
        edit: ['admin', 'hosphistory:edit'],
        del: ['admin', 'hosphistory:del']
      },
      rules: {
        subjid: [
          { required: true, message: '患者编号不能为空', trigger: 'blur' }
        ]
      },
      queryTypeOptions: [
        { key: 'subjid', display_name: '患者编号' },
        { key: 'discdat', display_name: '出院日期（年/月/日）' }
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
