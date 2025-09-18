<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <div v-if="crud.props.searchToggle">
        <!-- 搜索 -->
        <label class="el-form-item-label">患者编号</label>
        <el-input v-model="query.subjid" clearable placeholder="患者编号" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">呼吸道症状（是/否）</label>
        <el-input v-model="query.resptsyn" clearable placeholder="呼吸道症状（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">咳嗽（是/否）</label>
        <el-input v-model="query.coughyn" clearable placeholder="咳嗽（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">干咳（是/否）</label>
        <el-input v-model="query.drycouyn" clearable placeholder="干咳（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">乏力（是/否）</label>
        <el-input v-model="query.fatgyn" clearable placeholder="乏力（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">鼻塞 （是/否）</label>
        <el-input v-model="query.stufnsyn" clearable placeholder="鼻塞 （是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">流涕（是/否）</label>
        <el-input v-model="query.runnsyn" clearable placeholder="流涕（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">厌食 （是/否）</label>
        <el-input v-model="query.anrxyn" clearable placeholder="厌食 （是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">腹泻 （是/否）</label>
        <el-input v-model="query.diarhyn" clearable placeholder="腹泻 （是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">气喘（是/否）</label>
        <el-input v-model="query.astmyn" clearable placeholder="气喘（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">畏寒（是/否）</label>
        <el-input v-model="query.chilyn" clearable placeholder="畏寒（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">咽痛（是/否）</label>
        <el-input v-model="query.sothryn" clearable placeholder="咽痛（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">呼吸（次/分）</label>
        <el-input v-model="query.brethfrq" clearable placeholder="呼吸（次/分）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
        <label class="el-form-item-label">体温（是/否）</label>
        <el-input v-model="query.tempyn" clearable placeholder="体温（是/否）" style="width: 185px;" class="filter-item" @keyup.enter.native="crud.toQuery" />
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
          <el-form-item label="呼吸道症状（是/否）">
            <el-radio v-model="form.resptsyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="检查日期（年/月/日）">
            <el-date-picker v-model="form.rechkdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="咳嗽（是/否）">
            <el-radio v-model="form.coughyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="干咳（是/否）">
            <el-radio v-model="form.drycouyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="乏力（是/否）">
            <el-radio v-model="form.fatgyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="鼻塞 （是/否）">
            <el-radio v-model="form.stufnsyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="流涕（是/否）">
            <el-radio v-model="form.runnsyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="厌食 （是/否）">
            <el-radio v-model="form.anrxyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="腹泻 （是/否）">
            <el-radio v-model="form.diarhyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="气喘（是/否）">
            <el-radio v-model="form.astmyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="畏寒（是/否）">
            <el-radio v-model="form.chilyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="咽痛（是/否）">
            <el-radio v-model="form.sothryn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="检查日期（年/月/日）.1">
            <el-date-picker v-model="form.brchkdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="呼吸（次/分）">
            <el-radio v-model="form.brethfrq" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="体温（是/否）">
            <el-radio v-model="form.tempyn" v-for="item in dict.yes_no" :key="item.id" :label="item.value">{{ item.label }}</el-radio>
          </el-form-item>
          <el-form-item label="检查日期（年/月/日）.2">
            <el-date-picker v-model="form.techkdat" type="datetime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="体温（℃）">
            <el-input v-model="form.temp" style="width: 370px;" />
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
        <el-table-column prop="resptsyn" label="呼吸道症状（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.resptsyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="rechkdat" label="检查日期（年/月/日）" />
        <el-table-column prop="coughyn" label="咳嗽（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.coughyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="drycouyn" label="干咳（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.drycouyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="fatgyn" label="乏力（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.fatgyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="stufnsyn" label="鼻塞 （是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.stufnsyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="runnsyn" label="流涕（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.runnsyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="anrxyn" label="厌食 （是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.anrxyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="diarhyn" label="腹泻 （是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.diarhyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="astmyn" label="气喘（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.astmyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="chilyn" label="畏寒（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.chilyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="sothryn" label="咽痛（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.sothryn] }}
          </template>
        </el-table-column>
        <el-table-column prop="brchkdat" label="检查日期（年/月/日）.1" />
        <el-table-column prop="brethfrq" label="呼吸（次/分）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.brethfrq] }}
          </template>
        </el-table-column>
        <el-table-column prop="tempyn" label="体温（是/否）">
          <template slot-scope="scope">
            {{ dict.label.yes_no[scope.row.tempyn] }}
          </template>
        </el-table-column>
        <el-table-column prop="techkdat" label="检查日期（年/月/日）.2" />
        <el-table-column prop="temp" label="体温（℃）" />
        <el-table-column v-if="checkPer(['admin','respsymptoms:edit','respsymptoms:del'])" label="操作" width="150px" align="center">
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
import crudRespsymptoms from '@/api/respsymptoms'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { columnId: null, subjid: null, resptsyn: null, rechkdat: null, coughyn: null, drycouyn: null, fatgyn: null, stufnsyn: null, runnsyn: null, anrxyn: null, diarhyn: null, astmyn: null, chilyn: null, sothryn: null, brchkdat: null, brethfrq: null, tempyn: null, techkdat: null, temp: null }
export default {
  name: 'Respsymptoms',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  dicts: ['yes_no'],
  cruds() {
    return CRUD({ title: '呼吸道症状', url: 'api/respsymptoms', idField: 'columnId', sort: 'columnId,desc', crudMethod: { ...crudRespsymptoms }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'respsymptoms:add'],
        edit: ['admin', 'respsymptoms:edit'],
        del: ['admin', 'respsymptoms:del']
      },
      rules: {
      },
      queryTypeOptions: [
        { key: 'subjid', display_name: '患者编号' },
        { key: 'resptsyn', display_name: '呼吸道症状（是/否）' },
        { key: 'coughyn', display_name: '咳嗽（是/否）' },
        { key: 'drycouyn', display_name: '干咳（是/否）' },
        { key: 'fatgyn', display_name: '乏力（是/否）' },
        { key: 'stufnsyn', display_name: '鼻塞 （是/否）' },
        { key: 'runnsyn', display_name: '流涕（是/否）' },
        { key: 'anrxyn', display_name: '厌食 （是/否）' },
        { key: 'diarhyn', display_name: '腹泻 （是/否）' },
        { key: 'astmyn', display_name: '气喘（是/否）' },
        { key: 'chilyn', display_name: '畏寒（是/否）' },
        { key: 'sothryn', display_name: '咽痛（是/否）' },
        { key: 'brethfrq', display_name: '呼吸（次/分）' },
        { key: 'tempyn', display_name: '体温（是/否）' }
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
