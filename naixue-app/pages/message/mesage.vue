<template>
	<view class="content">
		<view class="input-box">
			<textarea placeholder="请输入你要留言的内容" class="textarea border" v-model="message.content"></textarea>
			<input
				type="text"
				class="border"
				style="height: 100rpx; margin-bottom: 20rpx;"
				v-model="message.name"
				placeholder="请输入用户名:"
			/>
			<button @click="addMsg" class="submit">提交</button>
		</view>
		<view class="list">
			<view class="item" v-for="(item,index) in listData" :key="index">
				<view class="word">
					<text class="user_name">{{item.name}}</text>
					<text class="content">{{item.content}}</text>
				</view>
				<text class="delete" @click="removeMsg(item._id)">删除</text>
			</view>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			message: {
				name: '',
				content: ''
			},
			listData: [
				{
					_id: '',
					name: '',
					content: ''
				}
			]
		};
	},
	onLoad() {
		this.getData();
	},

	methods: {
		addMsg() {
			
			let data = this.message;
			uniCloud
				.callFunction({
					name: 'add',
					data: this.message,
				})
				.then(res => {
					this.getData();
					this.message.name = '';
					this.message.content = '';
				});
		},
		getData() {
			console.log(this.listData.length)
			uniCloud
				.callFunction({
					name: 'get'
				})
				.then(res => {
					this.listData = res.result.data;
				});
		},
		removeMsg(id) {
			let _id = id;
			uniCloud
				.callFunction({
					name: 'remove',
					data: {
						_id
					}
				})
				.then(res => {
					this.getData();
				});
		}
	}
};
</script>

<style>
.content {
	display: flex;
	flex-direction: column;
	height: 100vh;
}
.input-box {
	height: 550rpx;
	padding: 20rpx;
	
}
.textarea{
	width: 690rpx;
	height: 300rpx;
	margin-bottom: 20rpx;
	padding-left: 20rpx;
	padding-top: 20rpx;
	
}
.border {
	border: 0.5px solid #afb0b2;
}
.list {
	display: flex;
	flex-direction: column;
	height: 900rpx;
	margin: 10rpx;
}
.submit{
	background-color: #a67d85;
	color: #faf7f8;
}
.word{
	display: flex;
	width: 740rpx;
	height: 80rpx;
	
	
}
.user_name{
	color: #a67d85;
	font-size: 14px;
}
.content{
	color: #808080;
	font-size: 12px;
}
.delete{
	display: flex;
	align-items: center;
	justify-content: center;
	width: 80rpx;
	height: 60rpx;
	border-radius: 3px;
	background-color: #965157;
	color: #ffffff;
}
.item{
	width: 740rpx;
	height: 200rpx;
	display: flex;
	flex-direction: column;
	
}
</style>
