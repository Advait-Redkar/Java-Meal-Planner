<template>
  <div class="main-div">
      <div class="div-subclass">
          <div class="recipe-name">   
      <h1>Meal List</h1>
       </div>
<div v-for="meal in meals" v-bind:key="meal.mealId">
          <div>
              <h2>{{meal.mealName}}</h2>
             <!-- <p class="list-button">{{meal.mealName}}</p> -->

</div>
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