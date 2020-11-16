<template>
	<view><my-audio ></my-audio></view>
</template>

<script>
import MyAudio from '../../components/myaudio.vue';
export default {
	data() {
		return {
			list: []
		};
	},
	components: {
		MyAudio
	},
	onLoad() {
		
	},
	methods: {}
};
</script>

<style></style>
