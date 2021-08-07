<template>
  <div>
<div v-for="meal in meals" v-bind:key="meal.mealId">
          <div>
              <h2>{{meal.mealName}}</h2>
              </div>
</div>
  </div>
</template>

<script>
import MealService from '@/services/MealService'
export default {
 name:"meal-list",
    data(){
        return{
            meals:[]
        }
    },
    created(){
            MealService
                .viewMeals()
                .then((response)=>{
                    this.meals = response.data;
                });
        }
    }

</script>

<style>

</style>