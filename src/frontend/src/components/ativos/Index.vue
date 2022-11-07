<template>
    <h1>Lista de Ativos</h1>
    

</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
        ativos : []
    }
  },
  mounted(){
    fetch("/ativos/").then((response)=>response.text()).then((data)=>{
        this.ativos = data;
    })
  }
}
</script>
