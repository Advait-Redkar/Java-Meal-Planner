<template>
  <div class="main-div">
      <div class="div-subclass">
          <div class="recipe-name">   
      <h1>Meal List</h1>
       <select v-model="mealSearch" class="search-bar">
            <option value=1>Breakfast</option>
            <option value=2>Lunch</option>
            <option value=3>Dinner</option>
            <option value=4>Snack</option>
        </select>
       </div>
<div v-for="meal in searchByType" v-bind:key="meal.mealId">
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
            meals:[],
            mealSearch:null
        }
    },
    created(){
            MealService
                .viewMeals()
                .then((response)=>{
                    this.meals = response.data;
                });
        },
        computed:{
            searchByType(){
                return this.meals.filter(meal=>{
                    if(!this.mealSearch){return true}
                    return meal.mealType == this.mealSearch;
                })
            }
        }
    }

</script>

<style>

</style>