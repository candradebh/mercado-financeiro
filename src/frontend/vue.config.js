module.exports = {
  // https://cli.vuejs.org/config/#devserver-proxy
  // transpileDependencies: true,
  //outputDir: 'target/dist',
  //assetsDir: 'static',
  devServer: {
    port: 3001,
    proxy: {
      '/': {
        target: 'http://localhost:9001',
        ws: false,
        changeOrigin: true
      }
    }
  },
  lintOnSave: false

}
