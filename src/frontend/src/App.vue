<template>
  <div id="nav">
    <router-link to="/">Ativos</router-link> |
    <router-link to="/show">Show</router-link>
  </div>
  <router-view />
</template>

<script>
export default {
  name: "App",
  computed: {
    username() {
      // We will see what `params` is shortly
      return this.$route.params.username;
    },
  },
  methods: {
    goToDashboard() {
      if (isAuthenticated) {
        this.$router.push("/dashboard");
      } else {
        this.$router.push("/login");
      }
    },
  },
};
</script>


