<template>
	<view class="container">
		<view class="form-box">
			<view class="form">
				<list-cell :hover="false">
					<view class="form-input">
						<view class="label">收货人</view>
						<input
							class="input"
							placeholder="请输入收货人"
							v-model="form.accept_name"
							placeholder-class="text-color-assist"
						/>
					</view>
				</list-cell>
				<list-cell :hover="false">
					<view class="form-input">
						<view class="label">性别</view>
						<view class="radio-group">
							<view
								class="radio"
								:class="{ checked: !form.gender }"
								style="margin-right: 10rpx;"
								@tap="form.gender = 0"
							>
								先生
							</view>
							<view class="radio" :class="{ checked: form.gender }" @tap="form.gender = 1">女士</view>
						</view>
					</view>
				</list-cell>
				<list-cell :hover="false">
					<view class="form-input">
						<view class="label">联系方式</view>
						<input
							class="input"
							placeholder="请输入收货人联系方式"
							v-model="form.mobile"
							placeholder-class="text-color-assist"
						/>
					</view>
				</list-cell>
				<list-cell :hover="false">
					<view class="form-input">
						<view class="label">所在区域</view>
						<input
							class="input"
							placeholder="请选择收货地址"
							v-model="form.area"
							placeholder-class="text-color-assist"
						/>
					</view>
				</list-cell>
				<list-cell :hover="false">
					<view class="form-input">
						<view class="label">收货地址</view>
						<input
							class="input"
							placeholder="请输入收货人详细地址"
							v-model="form.street"
							placeholder-class="text-color-assist"
						/>
					</view>
				</list-cell>
			</view>
			<view class="btn-section"><button type="primary" size="default" @click="save">保存</button></view>
		</view>
	</view>
</template>

<script></script>

<style></style>
