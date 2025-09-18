<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">患者编号</label>
        <el-input v-model="query.subjid" clearable placeholder="患者编号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">尿常规（是/否）</label>
        <el-input v-model="query.uryn" clearable placeholder="尿常规（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">检查日期（年/月/日）</label>
        <el-input v-model="query.urchkdat" clearable placeholder="检查日期（年/月/日）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
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
          <el-form-item label="尿常规（是/否）">
            <el-radio v-model="form.uryn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="检查日期（年/月/日）">
            <el-date-picker v-model="form.urchkdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿白细胞-检查结果">
            <el-input v-model="form.ur1res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿白细胞-单位">
            <el-input v-model="form.ur1unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿白细胞-结果判定">
            <el-input v-model="form.ur1clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿白细胞-备注">
            <el-input v-model="form.ur1oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿红细胞-检查结果">
            <el-input v-model="form.ur2res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿红细胞-单位">
            <el-input v-model="form.ur2unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿红细胞-结果判定">
            <el-input v-model="form.ur2clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿红细胞-备注">
            <el-input v-model="form.ur2oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿糖-检查结果">
            <el-input v-model="form.ur3res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿糖-单位">
            <el-input v-model="form.ur3unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿糖-结果判定">
            <el-input v-model="form.ur3clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="尿糖-备注">
            <el-input v-model="form.ur3oth" style="width: 370px;" />
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
        <el-table-column prop="uryn" label="尿常规（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.uryn] }}
          </template>
        </el-table-column>
        <el-table-column prop="urchkdat" label="检查日期（年/月/日）" />
        <el-table-column prop="ur1res" label="尿白细胞-检查结果" />
        <el-table-column prop="ur1unit" label="尿白细胞-单位" />
        <el-table-column prop="ur1clsig" label="尿白细胞-结果判定" />
        <el-table-column prop="ur1oth" label="尿白细胞-备注" />
        <el-table-column prop="ur2res" label="尿红细胞-检查结果" />
        <el-table-column prop="ur2unit" label="尿红细胞-单位" />
        <el-table-column prop="ur2clsig" label="尿红细胞-结果判定" />
        <el-table-column prop="ur2oth" label="尿红细胞-备注" />
        <el-table-column prop="ur3res" label="尿糖-检查结果" />
        <el-table-column prop="ur3unit" label="尿糖-单位" />
        <el-table-column prop="ur3clsig" label="尿糖-结果判定" />
        <el-table-column prop="ur3oth" label="尿糖-备注" />
        <el-table-column v-if="checkPer(['admin','urinalysis:edit','urinalysis:del'])" label="操作" width="150px" align="center">
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
import crudUrinalysis from '@/api/urinalysis'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { columnId: null, subjid: null, uryn: null, urchkdat: null, ur1res: null, ur1unit: null, ur1clsig: null, ur1oth: null, ur2res: null, ur2unit: null, ur2clsig: null, ur2oth: null, ur3res: null, ur3unit: null, ur3clsig: null, ur3oth: null }
export default {
  name: 'Urinalysis',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['yes_no'],
  cruds() {
    return CRUD({ title: '尿常规', url: 'api/urinalysis', idField: 'columnId', sort: 'columnId,desc', crudMethod: { ...crudUrinalysis }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'urinalysis:add'],
        edit: ['admin', 'urinalysis:edit'],
        del: ['admin', 'urinalysis:del']
      },
      rules: {
      },
      queryTypeOptions: [
        { key: 'subjid', display_name: '患者编号' },
        { key: 'uryn', display_name: '尿常规（是/否）' },
        { key: 'urchkdat', display_name: '检查日期（年/月/日）' }
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
