<template>
  <div>
      <edit-meal-plan v-bind:mealplanId="parseInt($route.params.mealplanId)">
      </edit-meal-plan>
  </div>
</template>

<script>
import EditMealPlan from '../components/EditMealPlan'


export default {
components:{
    EditMealPlan
}
}
</script>

<style>

</style>