import { createWebHistory, createRouter } from "vue-router";
import NotFound from "@/components/errors/NotFound.vue";
import ListAtivos from "@/views/ativos/Ativos.vue";
import ShowAtivo from "@/views/ativos/Show.vue";
import EditAtivo from "@/views/ativos/Edit.vue";

const routes = [
  {
    path: "/:catchAll(.*)",
    component: NotFound,
  },
  {
    path: "/",
    name: "Ativos",
    component: ListAtivos,
  },
  {
    path: "/show/:ticker",
    name: "ShowAtivo",
    component: ShowAtivo,
    props: true
  },
  {
    path: "/edit/:ticker",
    name: "EditarAtivo",
    component: EditAtivo,
    props: true
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;