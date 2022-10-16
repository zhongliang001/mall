import { createApp } from "vue";
import { createPinia } from "pinia";

import "./shims.d.ts";
import ElementPlus from "element-plus";
import zhCn from "element-plus/dist/locale/zh-cn.mjs";
import "element-plus/dist/index.css";

import App from "./App.vue";
import router from "./router";
import ZlComponent from "@/lib/ZlComponent.js";
import service from "./lib/service";

const app = createApp(App);

app.use(createPinia());
app.use(router);
app.use(ElementPlus, {
  locale: zhCn,
});
app.use(ZlComponent);
app.config.globalProperties.$service = service;
app.mount("#app");
