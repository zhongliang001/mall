import Vue from 'vue'
import {
  Button, Input, Form, FormItem, Main, Container, Header,
  ButtonGroup, Aside, Menu, MenuItem, Submenu, Tabs, TabPane, Card,
  Descriptions, DescriptionsItem, Table, TableColumn,
  Col, Row, Pagination, Dialog, Message, Loading, MessageBox
} from 'element-ui'

import ZlComponent from '../lib/ZlComponent.js'

Vue.use(Button)
Vue.use(Input)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Main)
Vue.use(Container)
Vue.use(Header)
Vue.use(ButtonGroup)
Vue.use(Aside)
Vue.use(Menu)
Vue.use(MenuItem)
Vue.use(Submenu)
Vue.use(Tabs)
Vue.use(TabPane)
Vue.use(Card)
Vue.use(Descriptions)
Vue.use(DescriptionsItem)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Col)
Vue.use(Row)
Vue.use(Pagination)
Vue.use(Dialog)
Vue.use(Loading)
Vue.use(ZlComponent)
Vue.prototype.$message = Message
Vue.prototype.$confirm = MessageBox.confirm
