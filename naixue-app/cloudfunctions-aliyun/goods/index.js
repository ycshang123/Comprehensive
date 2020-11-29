'use strict';
const db = uniCloud.database()
exports.main = async (event, context) => {
	const res  = await db.collection('category').aggregate().lookup({
		from:'goods',
		localField:'good_type',
		foreignField:'good_type',
		as:'goods_list'
	}).end()

	return res
};
