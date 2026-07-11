# 🏨 Airbnb Backend API

This is the "engine" behind an online hotel-booking website, similar to **Airbnb** or **Booking.com**. It handles everything that happens behind the scenes when someone searches for a hotel, books a room, and pays for it.
It includes inventory management, booking flow, user authentication, and hotel browsing.


## 🔗 Important Links

| What it is                              | Link |
|-----------------------------------------|------|
| 📖 Interactive API Documentation        | https://archit-tyagi.github.io/AirBnb_Project/ |
| 🐳 Docker Image                         | https://hub.docker.com/repository/docker/archittyagi221/airbnb/general |

The **Swagger** link opens a friendly web page where you can see every feature and even try it out live — no coding required.

## ✨ What can it do?

### For travelers (guests)
- Create an account and log in securely
- Search for hotels by city and travel dates
- See hotel details, rooms, photos, and amenities
- Book a room and add guest details
- Pay securely by card (handled by Stripe)
- Cancel a booking and get an automatic refund
- View all past and upcoming bookings
- Keep a personal list of frequent guests

### For hotel owners / managers
- Add and manage their own hotels and rooms
- Open a hotel for bookings ("go live")
- Control room availability and prices day-by-day
- Benefit from **automatic smart pricing** that reacts to demand, holidays, how full the hotel is, and last-minute urgency
- See booking and revenue **reports** for any date range

## 💡 A few things that make it interesting
- **Smart (dynamic) pricing** — room prices adjust automatically based on demand, holidays, occupancy, and urgency, just like real travel sites.
- **Secure payments** — card payments go through **Stripe**; the app never sees or stores card numbers.
- **Safe booking** — the system holds your room while you pay and prevents two people from grabbing the last room at the same time.

## 🛠️ What it's built with

| Piece | What it's for (in plain terms) |
|-------|-------------------------------|
| Java 21 + Spring Boot | The core framework the app runs on |
| MySQL | The database that stores hotels, rooms, users, and bookings |
| Stripe | Securely processes card payments |
| JSON Web Tokens (JWT) | Keeps users securely logged in |
| Swagger / OpenAPI | The interactive documentation you can click through |
| Docker | Lets you run the whole app without installing all the tools by hand |
| Maven | Assembles and builds the project |


## ⚙️ Settings you can change

The app runs out-of-the-box with sensible defaults. To connect it to your own database or payment account, set any of these as environment variables:

| Setting | What it does | Default |
|---------|--------------|---------|
| `MYSQL_HOST` | Where the database lives | `localhost` |
| `MYSQL_PORT` | Database port | `3306` |
| `MYSQL_DB` | Database name | `airbnbDB` |
| `MYSQL_USERNAME` | Database username | `root` |
| `MYSQL_PASSWORD` | Database password | `root` |
| `AIRBNB_SERVICE_PORT` | Port the app runs on | `8080` |
| `STRIPE_SECRET_KEY` | Your Stripe key for taking payments | a test key (fine for demos) |
| `STRIPE_WEBHOOK_SECRET` | Stripe security key for confirming payments | a test key |
| `HOLIDAY_CALENDAR` | Country whose holidays affect pricing | `INDIA` |

> 💳 Payments use Stripe **test** keys by default, so you can try the full "book and pay" flow with a fake test card (e.g. `4242 4242 4242 4242`) without spending real money.

## 📖 How a booking works (step by step)

1. A traveler **searches** for hotels in a city for their dates.
2. They pick a hotel and **start a booking** — the room is held for them for a short time.
3. They **add guest details**.
4. They **pay** securely by card.
5. Once payment succeeds, the booking is **confirmed** automatically.
6. If plans change, they can **cancel** for an automatic refund.

## 🗄️Schema Diagram

![Image](https://github.com/user-attachments/assets/bc209296-e0f2-48f9-a7ae-65d084e4cb6c)
