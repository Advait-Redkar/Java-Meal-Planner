<template>
  <div>
<div v-for="mealplan in mealPlans" v-bind:key="mealplan.mealplanId">
          <div>
              <h2>{{mealplan.mealplanName}}</h2>
              </div>
</div>
  </div>
</template>

<script>
import mealPlanService from '@/services/mealPlanService'
export default {
 name:"meal-plan-list",
    data(){
        return{
            mealPlans:[]
        }
    },
    created(){
            mealPlanService
                .viewMealPlans()
                .then((response)=>{
                    this.mealPlans = response.data;
                });
        }
    }

</script>

<style>

</style>