<template>
  <div class="main-div">
  <div class="div-subclass">
      <div class="recipe-name">   
      <h1>Meal Plan List</h1>
       </div>
<div v-for="mealplan in mealPlans" v-bind:key="mealplan.mealplanId">
          <div>
              <h2>{{mealplan.mealplanName}}</h2>
              </div>
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