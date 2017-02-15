module.exports = {
	entry: './src/index.jsx', // 输入文件
	output: { // 输出文件
		path: '../webapp',
		filename: 'bundle.js'
	},
	module: { // 添加模块
		loaders: [{ // 模块加载器
		    test: /\.js[x]?$/, // 搜索以.js,.jsx结尾的文件
		    exclude: /node_modules/, // 打包时过滤掉这个文件夹
		    loader: 'babel-loader'
		}],
	},
	resolve:{
        extensions:['.js','.jsx'] //自动补全后缀，所以在页面引用的时候不用写这些后缀名
    },
}
