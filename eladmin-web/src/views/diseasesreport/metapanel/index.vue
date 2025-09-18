<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">患者编号</label>
        <el-input v-model="query.subjid" clearable placeholder="患者编号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">血生化 （是/否）</label>
        <el-input v-model="query.bcyn" clearable placeholder="血生化 （是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">检查日期（年/月/日）</label>
        <el-input v-model="query.bcchkdat" clearable placeholder="检查日期（年/月/日）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
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
          <el-form-item label="血生化 （是/否）">
            <el-radio v-model="form.bcyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="检查日期（年/月/日）">
            <el-date-picker v-model="form.bcchkdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="谷草转氨酶(AST) -检查结果">
            <el-input v-model="form.bc1res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="谷草转氨酶(AST) -单位">
            <el-input v-model="form.blc1unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="谷草转氨酶(AST) -结果判定">
            <el-input v-model="form.bc1clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="谷草转氨酶(AST) -备注">
            <el-input v-model="form.bc1oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="谷丙转氨酶（ALT）-检查结果">
            <el-input v-model="form.bc2res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="谷丙转氨酶（ALT）-单位">
            <el-input v-model="form.bc2unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="谷丙转氨酶（ALT）-结果判定">
            <el-input v-model="form.bc2clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="谷丙转氨酶（ALT）-备注">
            <el-input v-model="form.bc2oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="肌酸激酶（CK）-检查结果">
            <el-input v-model="form.bc3res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="肌酸激酶（CK）单位">
            <el-input v-model="form.bc3unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="肌酸激酶（CK）-结果判定">
            <el-input v-model="form.bc3clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="肌酸激酶（CK）-备注">
            <el-input v-model="form.bc3oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="肌酸激酶同工酶（CKMB）-检查结果">
            <el-input v-model="form.bc4res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="肌酸激酶同工酶（CKMB）-单位">
            <el-input v-model="form.bc4unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="肌酸激酶同工酶（CKMB）-结果判定">
            <el-input v-model="form.bc4clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="肌酸激酶同工酶（CKMB）-备注">
            <el-input v-model="form.bc4oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="碱性磷酸酶（ALP）-检查结果">
            <el-input v-model="form.bc5res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="碱性磷酸酶（ALP）-单位">
            <el-input v-model="form.bc5unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="碱性磷酸酶（ALP）-结果判定">
            <el-input v-model="form.bc5clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="碱性磷酸酶（ALP）-备注">
            <el-input v-model="form.bc5oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="乳酸脱氢酶（LDH）-检查结果">
            <el-input v-model="form.bc6res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="乳酸脱氢酶（LDH）-单位">
            <el-input v-model="form.bc6unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="乳酸脱氢酶（LDH）-结果判定">
            <el-input v-model="form.bc6clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="乳酸脱氢酶（LDH）-备注">
            <el-input v-model="form.bc6oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿素氮（UREA）-检查结果">
            <el-input v-model="form.bc7res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿素氮（UREA）-单位">
            <el-input v-model="form.bc7unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿素氮（UREA）-结果判定">
            <el-input v-model="form.bc7clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿素氮（UREA）-备注">
            <el-input v-model="form.bc7oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血肌酐（CREA）-检查结果">
            <el-input v-model="form.bc8res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血肌酐（CREA）-单位">
            <el-input v-model="form.bc8unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血肌酐（CREA）-结果判定">
            <el-input v-model="form.bc8clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血肌酐（CREA）-备注">
            <el-input v-model="form.bc8oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="Ca2+检查结果">
            <el-input v-model="form.bc9res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="Ca2+单位">
            <el-input v-model="form.bc9unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="Ca2+结果判定">
            <el-input v-model="form.bc9clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="Ca2+备注">
            <el-input v-model="form.bc9oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="Na+检查结果">
            <el-input v-model="form.bc10res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="Na+单位">
            <el-input v-model="form.bc10uni" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="Na+结果判定">
            <el-input v-model="form.bc10clsi" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="Na+备注">
            <el-input v-model="form.bc10rem" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="Cl-检查结果">
            <el-input v-model="form.bc11res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="Cl-单位">
            <el-input v-model="form.bc11uni" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="Cl-结果判定">
            <el-input v-model="form.bc11clsi" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="Cl-备注">
            <el-input v-model="form.bc11rem" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="K+检查结果">
            <el-input v-model="form.bc12res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="K+单位">
            <el-input v-model="form.bc12uni" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="K+结果判定">
            <el-input v-model="form.bc12clsi" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="K+备注">
            <el-input v-model="form.bc12rem" style="width: 370px;" />
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
        <el-table-column prop="bcyn" label="血生化 （是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.bcyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="bcchkdat" label="检查日期（年/月/日）" />
        <el-table-column prop="bc1res" label="谷草转氨酶(AST) -检查结果" />
        <el-table-column prop="blc1unit" label="谷草转氨酶(AST) -单位" />
        <el-table-column prop="bc1clsig" label="谷草转氨酶(AST) -结果判定" />
        <el-table-column prop="bc1oth" label="谷草转氨酶(AST) -备注" />
        <el-table-column prop="bc2res" label="谷丙转氨酶（ALT）-检查结果" />
        <el-table-column prop="bc2unit" label="谷丙转氨酶（ALT）-单位" />
        <el-table-column prop="bc2clsig" label="谷丙转氨酶（ALT）-结果判定" />
        <el-table-column prop="bc2oth" label="谷丙转氨酶（ALT）-备注" />
        <el-table-column prop="bc3res" label="肌酸激酶（CK）-检查结果" />
        <el-table-column prop="bc3unit" label="肌酸激酶（CK）单位" />
        <el-table-column prop="bc3clsig" label="肌酸激酶（CK）-结果判定" />
        <el-table-column prop="bc3oth" label="肌酸激酶（CK）-备注" />
        <el-table-column prop="bc4res" label="肌酸激酶同工酶（CKMB）-检查结果" />
        <el-table-column prop="bc4unit" label="肌酸激酶同工酶（CKMB）-单位" />
        <el-table-column prop="bc4clsig" label="肌酸激酶同工酶（CKMB）-结果判定" />
        <el-table-column prop="bc4oth" label="肌酸激酶同工酶（CKMB）-备注" />
        <el-table-column prop="bc5res" label="碱性磷酸酶（ALP）-检查结果" />
        <el-table-column prop="bc5unit" label="碱性磷酸酶（ALP）-单位" />
        <el-table-column prop="bc5clsig" label="碱性磷酸酶（ALP）-结果判定" />
        <el-table-column prop="bc5oth" label="碱性磷酸酶（ALP）-备注" />
        <el-table-column prop="bc6res" label="乳酸脱氢酶（LDH）-检查结果" />
        <el-table-column prop="bc6unit" label="乳酸脱氢酶（LDH）-单位" />
        <el-table-column prop="bc6clsig" label="乳酸脱氢酶（LDH）-结果判定" />
        <el-table-column prop="bc6oth" label="乳酸脱氢酶（LDH）-备注" />
        <el-table-column prop="bc7res" label="尿素氮（UREA）-检查结果" />
        <el-table-column prop="bc7unit" label="尿素氮（UREA）-单位" />
        <el-table-column prop="bc7clsig" label="尿素氮（UREA）-结果判定" />
        <el-table-column prop="bc7oth" label="尿素氮（UREA）-备注" />
        <el-table-column prop="bc8res" label="血肌酐（CREA）-检查结果" />
        <el-table-column prop="bc8unit" label="血肌酐（CREA）-单位" />
        <el-table-column prop="bc8clsig" label="血肌酐（CREA）-结果判定" />
        <el-table-column prop="bc8oth" label="血肌酐（CREA）-备注" />
        <el-table-column prop="bc9res" label="Ca2+检查结果" />
        <el-table-column prop="bc9unit" label="Ca2+单位" />
        <el-table-column prop="bc9clsig" label="Ca2+结果判定" />
        <el-table-column prop="bc9oth" label="Ca2+备注" />
        <el-table-column prop="bc10res" label="Na+检查结果" />
        <el-table-column prop="bc10uni" label="Na+单位" />
        <el-table-column prop="bc10clsi" label="Na+结果判定" />
        <el-table-column prop="bc10rem" label="Na+备注" />
        <el-table-column prop="bc11res" label="Cl-检查结果" />
        <el-table-column prop="bc11uni" label="Cl-单位" />
        <el-table-column prop="bc11clsi" label="Cl-结果判定" />
        <el-table-column prop="bc11rem" label="Cl-备注" />
        <el-table-column prop="bc12res" label="K+检查结果" />
        <el-table-column prop="bc12uni" label="K+单位" />
        <el-table-column prop="bc12clsi" label="K+结果判定" />
        <el-table-column prop="bc12rem" label="K+备注" />
        <el-table-column v-if="checkPer(['admin','metapanel:edit','metapanel:del'])" label="操作" width="150px" align="center">
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
import crudMetapanel from '@/api/metapanel'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { columnId: null, subjid: null, bcyn: null, bcchkdat: null, bc1res: null, blc1unit: null, bc1clsig: null, bc1oth: null, bc2res: null, bc2unit: null, bc2clsig: null, bc2oth: null, bc3res: null, bc3unit: null, bc3clsig: null, bc3oth: null, bc4res: null, bc4unit: null, bc4clsig: null, bc4oth: null, bc5res: null, bc5unit: null, bc5clsig: null, bc5oth: null, bc6res: null, bc6unit: null, bc6clsig: null, bc6oth: null, bc7res: null, bc7unit: null, bc7clsig: null, bc7oth: null, bc8res: null, bc8unit: null, bc8clsig: null, bc8oth: null, bc9res: null, bc9unit: null, bc9clsig: null, bc9oth: null, bc10res: null, bc10uni: null, bc10clsi: null, bc10rem: null, bc11res: null, bc11uni: null, bc11clsi: null, bc11rem: null, bc12res: null, bc12uni: null, bc12clsi: null, bc12rem: null }
export default {
  name: 'Metapanel',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['yes_no'],
  cruds() {
    return CRUD({ title: '血生化', url: 'api/metapanel', idField: 'columnId', sort: 'columnId,desc', crudMethod: { ...crudMetapanel }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'metapanel:add'],
        edit: ['admin', 'metapanel:edit'],
        del: ['admin', 'metapanel:del']
      },
      rules: {
      },
      queryTypeOptions: [
        { key: 'subjid', display_name: '患者编号' },
        { key: 'bcyn', display_name: '血生化 （是/否）' },
        { key: 'bcchkdat', display_name: '检查日期（年/月/日）' }
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
