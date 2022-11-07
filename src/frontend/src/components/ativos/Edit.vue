<template>
  <h3>Editando {{ ticker }}</h3>
</template>

<script>
export default {
  name: "Edit",
  props: {
    ticker: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      ativo: {},
    };
  },
  created() {
    console.log(this.ativo);
  },
  mounted() {
    fetch("/ativos/" + this.ticker)
      .then((response) => response.json())
      .then((data) => (this.ativo = data));
  },
};
</script>
