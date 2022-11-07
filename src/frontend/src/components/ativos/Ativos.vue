<template>
    <h1>Lista de Ativos</h1>
    <table>
      <thead>
        <tr>
          <td>Ticker</td>
          <td>Nome</td>
          <td>Tipo</td>
          <td>CNPJ</td>
          <td>-</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="row in ativos" :key="row.ticker">
          <td>{{row.ticker}}</td>
          <td>{{row.nome}}</td>
          <td>{{row.tipoAtivo}}</td>
          <td>{{row.cnpj}}</td>
          <td><router-link :to="'/show/'+ row.ticker">Show</router-link> </td>
        </tr>
      </tbody>
    </table>
    

</template>

<script>
import Show from './Show.vue'

export default {
    name: "Ativos",
    data() {
        return {
            ativos: []
        };
    },
    mounted() {
        fetch("/ativos/").then(response => response.json()).then(data => (this.ativos = data));
    },
    components: { Show }
}
</script>
