<template>
  <h3>Editando {{ ticker }}</h3>
  <form v-on:submit="updateAtivo">
    <div class="mb-3">
      <label for="ticker" class="form-label">Ticker</label>
      <input
        class="form-control"
        type="text"
        id="ticker"
        placeholder="TICKER"
        aria-label="Ticker do ativo"
        v-model="ativo.ticker"
        disabled
      />
    </div>

    <div class="mb-3">
      <label for="nome" class="form-label">Nome</label>
      <input
        class="form-control"
        type="text"
        id="nome"
        placeholder="VALE SA ON/PN"
        v-model="ativo.nome"
      />
    </div>

    <div class="mb-3">
      <label for="cnpj" class="form-label">CNPJ</label>
      <input
        class="form-control"
        type="text"
        id="cnpj"
        placeholder="CNPJ"
        aria-label="CNPJ do ativo"
        v-model="ativo.cnpj"
      />
    </div>

    <button type="submit" class="btn btn-primary">Salvar</button>
  </form>
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
  methods: {
    updateAtivo(e) {
      e.preventDefault(); // Prevent page from reloading.

      const requestOptions = {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.ativo),
      };

      fetch("/ativos/" + this.ativo.ticker, requestOptions)
        .then((response) => response.json())
        .then((data) => (this.ativo = data));

      this.$router.push({ name: "Ativos" });
    },
  },

  mounted() {
    fetch("/ativos/" + this.ticker)
      .then((response) => response.json())
      .then((data) => (this.ativo = data));
  },
};
</script>
