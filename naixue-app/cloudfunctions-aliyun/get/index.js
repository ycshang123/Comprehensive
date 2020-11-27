'use strict';
const db = uniCloud.database()
exports.main = async (event, context) => {
	const collection = db.collection('message')
	const res = await collection.orderBy("_id","desc").get()
	//返回数据给客户端
	return res
};
