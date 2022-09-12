// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })
const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  publicPath: "/",

  configureWebpack() {
    return {
      resolve: {
        fallback: {
          http: require.resolve("stream-http"),
        },
      },
    }
  },
})


