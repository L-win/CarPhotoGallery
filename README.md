
#### About

CarPhotoGallery is an application that fetches and displays photos from internet using Unsplash API.

#### Stack

- retrofit
- compose
- viewmodel
- dependency injection

#### TODO

**Architecture**
- Data
	- Repository (interface, implementation)
- Model
	- MarsPhoto
- Network
	- MarsApiService (retrofit interface)
- DI
	- AppContainer
		- retrofit
		- retrofitService
		- repository
- Ui
	- screens
	- theme