import { createRouter, createWebHistory } from "vue-router";
import MainView from "../views/MainView.vue";
import LoginView from "../views/LoginView.vue";
import WorkbenchHome from "../views/WorkbenchHome.vue";
import SellManage from "../views/sell/SellManage.vue";
import ReleaseGoods from "../views/sell/ReleaseGoods.vue";
import Menu from "../views/config/Menu.vue";
import Action from "../views/config/Action.vue";
import Role from "../views/config/Role.vue";
import Dict from "../views/config/Dict.vue";
import User from "../views/config/User.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "login",
      component: LoginView,
    },
    {
      path: "/mainView",
      name: "mainView",
      component: MainView,
      children: [
        {
          path: "/workbenchHome",
          name: "workbenchHome",
          component: WorkbenchHome,
        },
        {
          path: "/sellManage",
          name: "sellManage",
          component: SellManage,
          children: [
            {
              path: "/releaseGoods",
              name: "releaseGoods",
              component: ReleaseGoods,
            },
          ],
        },
        {
          path: "/menu",
          name: "menu",
          component: Menu,
        },
        {
          path: "/action",
          name: "action",
          component: Action,
        },
        {
          path: "/role",
          name: "role",
          component: Role,
        },
        {
          path: "/user",
          name: "user",
          component: User,
        },
        {
          path: "/dict",
          name: "dict",
          component: Dict,
        },
      ],
    },
  ],
});

export default router;
