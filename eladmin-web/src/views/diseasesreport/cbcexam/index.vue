<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">患者编号</label>
        <el-input v-model="query.subjid" clearable placeholder="患者编号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">血常规（是/否）</label>
        <el-input v-model="query.blyn" clearable placeholder="血常规（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">检查日期（年/月/日）</label>
        <el-input v-model="query.blchkdat" clearable placeholder="检查日期（年/月/日）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">中性粒细胞计数-检查结果</label>
        <el-input v-model="query.bl1res" clearable placeholder="中性粒细胞计数-检查结果" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">淋巴细胞计数-检查结果</label>
        <el-input v-model="query.bl2res" clearable placeholder="淋巴细胞计数-检查结果" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">红细胞计数-检查结果</label>
        <el-input v-model="query.bl3res" clearable placeholder="红细胞计数-检查结果" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">血红蛋白-检查结果</label>
        <el-input v-model="query.bl4res" clearable placeholder="血红蛋白-检查结果" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">红细胞压积-检查结果</label>
        <el-input v-model="query.bl5res" clearable placeholder="红细胞压积-检查结果" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">白细胞计数-检查结果</label>
        <el-input v-model="query.bl6res" clearable placeholder="白细胞计数-检查结果" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">血小板计数-检查结果</label>
        <el-input v-model="query.bl7res" clearable placeholder="血小板计数-检查结果" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
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
          <el-form-item label="血常规（是/否）">
            <el-radio v-model="form.blyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="检查日期（年/月/日）">
            <el-date-picker v-model="form.blchkdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="中性粒细胞计数-检查结果">
            <el-input v-model="form.bl1res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="中性粒细胞计数-单位">
            <el-input v-model="form.bl1unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="中性粒细胞计数-结果判定">
            <el-input v-model="form.bl1clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="中性粒细胞计数备注">
            <el-input v-model="form.bl1oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="淋巴细胞计数-检查结果">
            <el-input v-model="form.bl2res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="淋巴细胞计数-单位">
            <el-input v-model="form.bl2unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="淋巴细胞计数-结果判定">
            <el-input v-model="form.bl2clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="淋巴细胞计数-备注">
            <el-input v-model="form.bl2oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="红细胞计数-检查结果">
            <el-input v-model="form.bl3res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="红细胞计数-单位">
            <el-input v-model="form.bl3unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="红细胞计数-结果判定">
            <el-input v-model="form.bl3clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="红细胞计数-备注">
            <el-input v-model="form.bl3oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血红蛋白-检查结果">
            <el-input v-model="form.bl4res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血红蛋白-单位">
            <el-input v-model="form.bl4unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血红蛋白-结果判定">
            <el-input v-model="form.bl4clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="form.bl4oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="红细胞压积-检查结果">
            <el-input v-model="form.bl5res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="红细胞压积-单位">
            <el-input v-model="form.bl5unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="红细胞压积-结果判定">
            <el-input v-model="form.bl5clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注.1">
            <el-input v-model="form.bl5oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="白细胞计数-检查结果">
            <el-input v-model="form.bl6res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="白细胞计数-单位">
            <el-input v-model="form.bl6unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="白细胞计数-结果判定">
            <el-input v-model="form.bl6clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="白细胞计数-备注">
            <el-input v-model="form.bl6oth" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血小板计数-检查结果">
            <el-input v-model="form.bl7res" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血小板计数-单位">
            <el-input v-model="form.bl7unit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血小板计数-结果判定">
            <el-input v-model="form.bl7clsig" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="血小板计数-备注">
            <el-input v-model="form.bl7oth" style="width: 370px;" />
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
        <el-table-column prop="blyn" label="血常规（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.blyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="blchkdat" label="检查日期（年/月/日）" />
        <el-table-column prop="bl1res" label="中性粒细胞计数-检查结果" />
        <el-table-column prop="bl1unit" label="中性粒细胞计数-单位" />
        <el-table-column prop="bl1clsig" label="中性粒细胞计数-结果判定" />
        <el-table-column prop="bl1oth" label="中性粒细胞计数备注" />
        <el-table-column prop="bl2res" label="淋巴细胞计数-检查结果" />
        <el-table-column prop="bl2unit" label="淋巴细胞计数-单位" />
        <el-table-column prop="bl2clsig" label="淋巴细胞计数-结果判定" />
        <el-table-column prop="bl2oth" label="淋巴细胞计数-备注" />
        <el-table-column prop="bl3res" label="红细胞计数-检查结果" />
        <el-table-column prop="bl3unit" label="红细胞计数-单位" />
        <el-table-column prop="bl3clsig" label="红细胞计数-结果判定" />
        <el-table-column prop="bl3oth" label="红细胞计数-备注" />
        <el-table-column prop="bl4res" label="血红蛋白-检查结果" />
        <el-table-column prop="bl4unit" label="血红蛋白-单位" />
        <el-table-column prop="bl4clsig" label="血红蛋白-结果判定" />
        <el-table-column prop="bl4oth" label="备注" />
        <el-table-column prop="bl5res" label="红细胞压积-检查结果" />
        <el-table-column prop="bl5unit" label="红细胞压积-单位" />
        <el-table-column prop="bl5clsig" label="红细胞压积-结果判定" />
        <el-table-column prop="bl5oth" label="备注.1" />
        <el-table-column prop="bl6res" label="白细胞计数-检查结果" />
        <el-table-column prop="bl6unit" label="白细胞计数-单位" />
        <el-table-column prop="bl6clsig" label="白细胞计数-结果判定" />
        <el-table-column prop="bl6oth" label="白细胞计数-备注" />
        <el-table-column prop="bl7res" label="血小板计数-检查结果" />
        <el-table-column prop="bl7unit" label="血小板计数-单位" />
        <el-table-column prop="bl7clsig" label="血小板计数-结果判定" />
        <el-table-column prop="bl7oth" label="血小板计数-备注" />
        <el-table-column v-if="checkPer(['admin','cbcexam:edit','cbcexam:del'])" label="操作" width="150px" align="center">
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
import crudCbcexam from '@/api/cbcexam'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { columnId: null, subjid: null, blyn: null, blchkdat: null, bl1res: null, bl1unit: null, bl1clsig: null, bl1oth: null, bl2res: null, bl2unit: null, bl2clsig: null, bl2oth: null, bl3res: null, bl3unit: null, bl3clsig: null, bl3oth: null, bl4res: null, bl4unit: null, bl4clsig: null, bl4oth: null, bl5res: null, bl5unit: null, bl5clsig: null, bl5oth: null, bl6res: null, bl6unit: null, bl6clsig: null, bl6oth: null, bl7res: null, bl7unit: null, bl7clsig: null, bl7oth: null }
export default {
  name: 'Cbcexam',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['yes_no'],
  cruds() {
    return CRUD({ title: '血常规', url: 'api/cbcexam', idField: 'columnId', sort: 'columnId,desc', crudMethod: { ...crudCbcexam }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'cbcexam:add'],
        edit: ['admin', 'cbcexam:edit'],
        del: ['admin', 'cbcexam:del']
      },
      rules: {
      },
      queryTypeOptions: [
        { key: 'subjid', display_name: '患者编号' },
        { key: 'blyn', display_name: '血常规（是/否）' },
        { key: 'blchkdat', display_name: '检查日期（年/月/日）' },
        { key: 'bl1res', display_name: '中性粒细胞计数-检查结果' },
        { key: 'bl2res', display_name: '淋巴细胞计数-检查结果' },
        { key: 'bl3res', display_name: '红细胞计数-检查结果' },
        { key: 'bl4res', display_name: '血红蛋白-检查结果' },
        { key: 'bl5res', display_name: '红细胞压积-检查结果' },
        { key: 'bl6res', display_name: '白细胞计数-检查结果' },
        { key: 'bl7res', display_name: '血小板计数-检查结果' }
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
