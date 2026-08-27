Project Structure
```
├── app/
│   │
│   ├── build.gradle.kts
│   │
│   └── src/
│       │
│       └── main/
│           │
│           ├── AndroidManifest.xml
│           │
│           ├── java/
│           │   └── com.example.recipeapp/
│           │       │
│           │       ├── data/
│           │       │   │
│           │       │   ├── local/
│           │       │   │   ├── AppDatabase.kt
│           │       │   │   ├── FavoriteDao.kt
│           │       │   │   └── FavoriteRecipe.kt
│           │       │   │
│           │       │   ├── remote/
│           │       │   │   ├── ApiService.kt
│           │       │   │   └── RetrofitInstance.kt
│           │       │   │
│           │       │   └── repository/
│           │       │       ├── RecipeRepository.kt
│           │       │       └── FavoriteRepository.kt
│           │       │
│           │       ├── model/
│           │       │   ├── Meal.kt
│           │       │
│           │       ├── ui/
│           │       │   │
│           │       │   ├── auth/
│           │       │   │   │
│           │       │   │   ├── AuthActivity.kt
│           │       │   │   ├── SplashFragment.kt
│           │       │   │   ├── LoginFragment.kt
│           │       │   │   └── RegisterFragment.kt
│           │       │   │
│           │       │   ├── recipe/
│           │       │   │   │
│           │       │   │   ├── RecipeActivity.kt
│           │       │   │   │
│           │       │   │   ├── home/
│           │       │   │   │   ├── HomeFragment.kt
│           │       │   │   │   ├── HomeViewModel.kt
│           │       │   │   │   └── RecipeAdapter.kt
│           │       │   │   │
│           │       │   │   ├── search/
│           │       │   │   │   ├── SearchFragment.kt
│           │       │   │   │   └── SearchViewModel.kt
│           │       │   │   │
│           │       │   │   ├── favorite/
│           │       │   │   │   ├── FavoriteFragment.kt
│           │       │   │   │   └── FavoriteViewModel.kt
│           │       │   │   │
│           │       │   │   └── details/
│           │       │   │       ├── RecipeDetailFragment.kt
│           │       │   │       └── RecipeDetailViewModel.kt
│           │       │   │
│           │       │   └── about/
│           │       │       └── AboutFragment.kt
│           │       │
│           │       └── utils/
│           │           ├── PreferencesManager.kt
│           │           └── Constants.kt
│           │
│           └── res/
│               │
│               ├── drawable/
│               │   └── ...
│               │
│               ├── layout/
│               │   │
│               │   ├── activity_auth.xml
│               │   ├── activity_recipe.xml
│               │   │
│               │   ├── fragment_splash.xml
│               │   ├── fragment_login.xml
│               │   ├── fragment_register.xml
│               │   ├── fragment_home.xml
│               │   ├── fragment_search.xml
│               │   ├── fragment_favorite.xml
│               │   ├── fragment_recipe_detail.xml
│               │   ├── fragment_about.xml
│               │   │
│               │   └── item_recipe.xml
│               │
│               ├── menu/
│               │   └── menu_recipe.xml
│               │
│               ├── navigation/
│               │   ├── nav_auth.xml
│               │   └── nav_recipe.xml
│               │
│               └── values/
│                   ├── colors.xml
│                   ├── themes.xml
│                   ├── strings.xml
```
