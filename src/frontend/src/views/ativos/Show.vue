<template>
  <h1>Ativo {{ ativo.ticker }}</h1>
  <p>Nome: {{ ativo.nome }}</p>
</template>

<script>
export default {
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
