import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import AddRecipeView from '@/views/AddRecipeView'
import AddIngredientView from '@/views/AddIngredientView'
import RecipeListView from '@/views/RecipeListView'
import RecipeDetailsView from '@/views/RecipeDetailsView'
import EditRecipeView from '@/views/EditRecipeView'
import CreateMealPlanView from '@/views/CreateMealPlanView'
import MealPlanListView from '@/views/MealPlanListView'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/add-recipe",
      name: "addRecipe",
      component: AddRecipeView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/add-ingredient",
      name: "addIngredient",
      component: AddIngredientView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/ingredients",
      name: "ingredients-list",
      component: AddRecipeView
    },
    {
      path: "/viewMyRecipes",
      name: "recipeList",
      component: RecipeListView
    },
    {
      path: "/recipe/:recipeId",
      name: "recipeDetails",
      component: RecipeDetailsView

    },
    {
      path:"/recipe/edit/:recipeId",
      name: "editRecipe",
      component: EditRecipeView
    },
    {
      path:"/createmealplan",
      name: "createMealPlan",
      component: CreateMealPlanView
    },
    {
      path:"/mealplans",
      name:"mealPlans",
      component: MealPlanListView
    }
    

    
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
