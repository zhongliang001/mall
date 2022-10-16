import ZlButton from "../components/ZlButton.vue";
import ZlTable from "../components/ZlTable.vue";

const components = [ZlButton, ZlTable];

const install = function (Vue) {
  components.forEach((component) => {
    Vue.component(component.name, component);
  });
};

/* istanbul ignore if */
if (typeof window !== "undefined" && window.Vue) {
  install(window.Vue);
}

export default {
  install,
  ZlButton,
  ZlTable,
};
