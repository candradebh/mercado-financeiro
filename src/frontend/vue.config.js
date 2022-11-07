module.exports = {
  // https://cli.vuejs.org/config/#devserver-proxy
  // transpileDependencies: true,
  //outputDir: 'target/dist',
  //assetsDir: 'static',
  devServer:{
    port:3000,
    proxy:{
        '/api':{
            target: 'http://localhost:8080',
            ws: true,
            changeOrigin: true
        }
    }
  }
}
