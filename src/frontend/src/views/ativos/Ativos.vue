<template>
  <h3>Lista de Ativos</h3>

  <table class="table table-striped table-hover">
    <thead>
      <tr>
        <th>TICKER</th>
        <th>Nome</th>
        <th>Tipo</th>
        <th>CNPJ</th>
        <th>Preço</th>
        <th>-</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="row in ativos" :key="row.ticker">
        <td>{{ row.ticker }}</td>
        <td>{{ row.nome }}</td>
        <td>{{ row.tipoAtivo }}</td>
        <td>{{ row.cnpj }}</td>
        <td>{{ row.preco }}</td>
        <td>
          <router-link :to="'/show/' + row.ticker">Ver</router-link> |
          <router-link :to="'/edit/' + row.ticker">Editar</router-link>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  data() {
    return {
      ativos: [],
    };
  },
  mounted() {
    fetch("/ativos/")
      .then((response) => response.json())
      .then((data) => (this.ativos = data));
  },
};
</script>
