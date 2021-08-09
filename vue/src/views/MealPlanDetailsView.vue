<template>
  <div>
<meal-plan-details v-bind:mealplan="mealplan"></meal-plan-details>
  </div>
</template>

<script>
import mealPlanService from '@/services/mealPlanService'
import MealPlanDetails from '@/components/MealPlanDetails'
export default {
    components: {
        MealPlanDetails
    },
    created(){
        mealPlanService.viewMealPlanDetails(this.$route.params.mealplanId)
        .then (response =>{
            this.mealplan=response.data;
        })
    },

    data(){
        return{
            mealplan:{}
        }
    }
}
</script>

<style>

</style>