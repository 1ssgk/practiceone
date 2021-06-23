module.exports = {  
    outputDir: "../src/main/resources/static",  
    indexPath: "../static/index.html",  
    devServer: {  
      // 프록시 설정
      proxy: {
        // 프록시 요청을 보낼 api의 시작 부분
        '/api': {
            // 프록시 요청을 보낼 서버의 주소
            target: 'http://localhost:8083'
        }
      }
    },  
    // chainWebpack: config => {  
    //   const svgRule = config.module.rule("svg");    
    //   svgRule.uses.clear();    
    //   svgRule.use("vue-svg-loader").loader("vue-svg-loader");  
    // }  
  };