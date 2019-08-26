<template>
  <div>
    <button @click="www">+</button>
    {{ count }}
  </div>
</template>

<script>
  import {mapMutations,mapState} from 'vuex';

  export default {
    data() {
      return {

      }
    },
    computed:{
      ...mapState('main',[
        'count'
      ])
    },
    asyncData(context) {
      return {
        project: 'nuxt'
      }
    },
    head() {
      return {
        title: '学院贴吧',
      }
    },
    methods:{
      ...mapMutations('main',[
        'increment'
      ]),
      www(){
        this.increment(this.count)
      }
    }
  }

</script>

<style>

</style>
