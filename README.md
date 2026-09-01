# 🍳 Recipe App

An Android app for browsing and searching recipes, letting users explore a variety of dishes, search by name, and save their favorites. This project was built as a **Final Project** as part of an Android development learning path.

## 📱 Features

- **Authentication** (Login & Register) with a Splash screen
- **Browse recipes** from [TheMealDB API](https://www.themealdb.com/api.php)
- **Search recipes** by name
- **Full recipe details** (ingredients, instructions, image)
- **Save favorite recipes** locally using Room database
- **About** page

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| **Kotlin** | Core programming language |
| **MVVM Architecture** | Code organization (Model - View - ViewModel) |
| **Retrofit** | Networking and API communication |
| **Room Database** | Local storage for favorite recipes |
| **Navigation Component** | Navigation between Fragments |
| **LiveData / ViewModel** | State management and UI binding |

## 🌐 API

The app fetches recipe data from **[TheMealDB API](https://www.themealdb.com/api.php)**, a free API providing recipes and meal data from around the world.

## 🏗️ Project Structure

```
app/src/main/java/com.example.recipeapp/
│
├── data/
│   ├── local/         # Room Database (favorites)
│   ├── remote/        # Retrofit API Service
│   └── repository/    # Repository layer
│
├── model/              # Data Models (Meal)
│
├── ui/
│   ├── auth/           # Login / Register / Splash
│   ├── recipe/
│   │   ├── home/       # Home screen
│   │   ├── search/     # Search
│   │   ├── favorite/   # Favorites
│   │   └── details/    # Recipe details
│   └── about/           # About page
│
```
## 📸 Screenshots
<!-- 
<img width="576" height="1280" alt="5816925327580663846_121" src="https://github.com/user-attachments/assets/d3460ac2-8586-495b-b4af-f62665cd81f1" />
<img width="576" height="1280" alt="5816925327580663845_121" src="https://github.com/user-attachments/assets/bd3d7170-7fe7-489e-aac8-7c40747830d7" />
<img width="576" height="1280" alt="5816925327580663844_121" src="https://github.com/user-attachments/assets/0f9b6b2b-6fc2-44cf-aa89-366d10663335" />
<img width="576" height="1280" alt="5816925327580663848_121" src="https://github.com/user-attachments/assets/14db4dff-fe78-4b95-a7a7-c27856fc26b0" />
<img width="576" height="1280" alt="5816925327580663849_121" src="https://github.com/user-attachments/assets/8ce06c85-7047-4cc9-a2c7-c7cebb43d960" />
<img width="576" height="1280" alt="5816925327580663850_121" src="https://github.com/user-attachments/assets/d532261a-f71c-4f0d-bcad-68e8aa4d8146" />
<img width="576" height="1280" alt="5816925327580663851_121" src="https://github.com/user-attachments/assets/6deca9bb-af6e-46d5-a0ec-6609d90589f7" />
-->

## 🎓 About This Project

This project was built as a **Final Project** to apply Android development fundamentals using Kotlin, covering:
- MVVM architecture
- Working with REST APIs
- Local storage using Room
- Navigation management with the Navigation Component

## 📄 License

This project is educational and was developed for learning purposes as part of course/graduation requirements.
