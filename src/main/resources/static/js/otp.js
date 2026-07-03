<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

    <head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>VaultX Bank | OTP Verification</title>

<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap"
      rel="stylesheet">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>

    <link rel="stylesheet" th:href="@{/css/style.css}">

    </head>

    <body>

    <div class="login-page">

        <!-- LEFT PANEL -->

        <div class="login-left">

            <div class="brand">

                <div class="bank-logo">
                    <i class="fa-solid fa-building-columns"></i>
                </div>

                <h1>VaultX Bank</h1>

                <h3>OTP Verification</h3>

                <p>
                    Enter the 6-digit OTP sent to your registered email address.
                </p>

            </div>

            <div class="illustration">

                <img th:src="@{/images/banking-login.svg}" alt="Bank">

            </div>

        </div>

        <!-- RIGHT PANEL -->

        <div class="login-right">

            <div class="login-card">

                <div class="card-header">

                    <h2>Verify OTP</h2>

                    <p>Enter the verification code</p>

                </div>

                <form>

                    <div class="otp-container">

                        <input class="otp-input" maxlength="1">
                            <input class="otp-input" maxlength="1">
                                <input class="otp-input" maxlength="1">
                                    <input class="otp-input" maxlength="1">
                                        <input class="otp-input" maxlength="1">
                                            <input class="otp-input" maxlength="1">

                    </div>

                    <div class="otp-timer">

                        00:60

                    </div>

                    <button
                        type="submit"
                        class="login-btn">

                        Verify OTP

                    </button>

                </form>

                <div class="register-link">

                    <a href="#">Resend OTP</a>

                </div>

                <div class="register-link">

                    <a th:href="@{/}">
                        ← Back to Login
                    </a>

                </div>

            </div>

        </div>

    </div>

    <script th:src="@{/js/otp.js}"></script>

    </body>
</html>