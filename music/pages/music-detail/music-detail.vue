<template>
	<!-- 根据neightStatus的值切换白天和黑夜模式 -->
	<view style="height: 100vh;" :class="nightStatus ? 'nightTheme' : ''">
		<view class="d-inline-block w-100 text-center py-4">
			<view>
				<text class="font">歌曲:</text>
				<text class="font-weight-bold">{{ audioName }}</text>
			</view>
			<view>
				<text class="font">歌手:</text>
				<text class="font-weight-bold">{{ singerName }}</text>
			</view>
		</view>
		<!-- 歌曲图片 -->
		<view class="flex align-center justify-center" style="height: 420rpx;">
			<image
				src="../../static/music2.jpg"
				lazy-load
				mode="widthFix"
				style="border-radius: 35rpx; box-shadow: 0 2rpx 6rpx 0;"
			></image>
		</view>
		<!-- 进度部分 -->
		<view class="flex align-center justify-center font" style="color: #7a8388;height: 45rpx;">
			<!-- 总时长 -->
			<view>{{ durationTime | formatTime }}</view>
			<!-- 进度条部分 -->
			<view style="width: 500rpx;">
				<slider
					block-size="16"
					activeColor="#e48267"
					backgroundColor="#eef2f3"
					:max="durationTime"
					:value="currentTime"
					@change="sliderToPlay"
					@changing="sliderToPlay"
				/>
			</view>
			<!-- 播放器 -->
			<view>{{ currentTime | formatTime }}</view>
		</view>
		<!-- 按扭部分 -->
		<view>
			<view class="flex align-center justify-center" style="padding-top: 20rpx;">
				<view class="mr-3" @tap="PreOrNext('pre')">
					<my-icon iconId="icon-shangyixiang" iconSize="85"></my-icon>
				</view>
				<view class="mx-5" @tap="PlayOrPause"><my-icon iconId="icon-bofang1" iconSize="80"></my-icon></view>
				<view class="ml-2" @tap="PreOrNext('next')">
					<my-icon iconId="icon-xiayixiang" iconSize="85"></my-icon>
				</view>
			</view>
			<view class="flex align-center justify-center  font text-light-black" style="padding-top: 20rpx;">
				<view class="flex flex-column align-center" @tap="chageStatus('listStatus')">
					<my-icon :iconId="!listStatus ? 'icon-icon--' : 'icon-liebiao'" iconSize="60"></my-icon>
					<text class="pt-1">播放列表</text>
				</view>
				<view class="flex flex-column align-center" style="padding: 0 80rpx;" @tap="chageStatus('listStatus')">
					<my-icon :iconId="!collectStatus ? 'icon-aixinfengxian' : 'icon-xihuan2'" iconSize="60"></my-icon>
					<text class="pt-1">收藏</text>
				</view>
				<view class="flex flex-column align-center" @tap="chageStatus('nightStatus')">
					<my-icon :iconId="!nightStatus ? 'icon-yejianmoshi' : 'icon-yueliang'" iconSize="60"></my-icon>
					<text class="pt-1">夜间模式</text>
				</view>
			</view>
		</view>
		<!-- 歌手具体信息 -->
		<view
			v-if="!listStatus"
			class="fixed-bottom shadow p-2 animated fadeInUp"
			style="height: 220rpx; border-radius: 30rpx; z-index: 0;"
		>
			<view class="flex justify-between">
				<view>
					<view>
						<text class="font">歌曲:</text>
						<text class="font-weight-bold">{{ audioName }}</text>
					</view>
					<view>
						<text class="font">歌手:</text>
						<text class="font-weight-bold">{{ singerName }}</text>
					</view>
				</view>
				<my-icon iconId="icon-jieshao" iconSize="65" @my-click="showSingerSynopsis"></my-icon>
			</view>
			<view>
				<view class="font-md pt-2">歌手简介:</view>
				<view class="text-ellipsis w-100">{{ singerSynopsis }}</view>
			</view>
		</view>

		<!-- 播放列表 -->
		<view class="fixed-bottom shadow p-2 animated fadeInUp" style="height: 220rpx; border-radius: 30rpx;" v-else>
			<scroll-view scroll-y style="height: 220rpx;">
				<block v-for="(item, index) in audioList" :key="item.id">
					<view
						class="flex align-center font px-2"
						style="height: 85rpx;"
						hover-class="bg-light"
						@tap="selectPlay(item.id)"
					>
						<text class="flex-1 text-ellipsis">{{ item.audioName }}</text>
						<text class="flex-1 text-ellipsis">{{ item.singerName }}</text>
						<view class="flex-1 ml-3 flex align-center">
							<text class="mr-2">播放</text>
							<my-icon iconId="icon-bofangsanjiaoxing" iconSize="40"></my-icon>
						</view>
					</view>
				</block>
			</scroll-view>
		</view>
		<uni-popup ref="popup" >
			<view  style="background-color: #F8F9FA; height: 600rpx; width: 650rpx; border-radius: 35rpx;" class="flex flex-wrap" >
				<view class="font ">{{ singerSynopsis }}</view>
			</view>
		</uni-popup>
	</view>
</template>

<script>
import unit from '../../common/unit.js';
import uniPopup from '../../components/uni-ui/uni-popup/uni-popup.vue';
import { mapState, mapGetters, mapMutations, mapActions } from 'vuex';
export default {
	data() {
		return {
			listStatus: false, //
			collectStatus: false,
			nightStatus: false
		};
	},
	components: {
		uniPopup
	},
	filters: {
		formatTime(num) {
			return unit.formatTime(num);
		}
	},
	computed: {
		...mapState({
			durationTime: ({ audio }) => audio.durationTime,
			currentTime: ({ audio }) => audio.currentTime,
			audioList: ({ audio }) => audio.audioList,
			playStatus: ({ audio }) => audio.playStatus
		}),
		...mapGetters(['audioName', 'singerName', 'singerSynopsis'])
	},
	methods: {
		// 改变状态
		chageStatus(statusType) {
			this[statusType] = !this[statusType];
		},
		// 展示歌手简介详情
		showSingerSynopsis() {
			this.$refs.popup.open();
		},
		...mapActions(['sliderToPlay', 'PlayOrPause', 'PreOrNext', 'selectPlay'])
	}
};
</script>

<style></style>
