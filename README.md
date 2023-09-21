<h1 align="center"> Email-to-Telegram<br </h1>
Программа отправки писем с e-mail в вашу группу в телеграме </h1>
<br>
<h2> Используемые библиотеки и технологии: </h2>
Для работы с почтой используется библиотека Javax.mail<br>
Для логирования используется библиотека log4j<br>
Отправка письма в телеграм осуществляется отправкой GET запроса на адрес api.telegram.org с использованием API телеграм бота<br>
Все необходимые настройки вносятся в программу через файл Config.properties<br>
Сборка в jar осуществлена посредством maven-compiler-plugin и maven-jar-plugin<br>
Программа работает с почтами Yandex, Mail, Gmail <br>
<br>
<br>
<h2> Инструкция по использованию: </h2>
1 Скачайте jar или exe файл, а также файл Config.properties. Перед запуском убедитесь, что файлы находятся в одной папке<br>
2 Откройте файл Config.properties любым текстовым редактором и заполните поля:<br> <ul>
  <li>Почтовый сервис. Введите туда YANDEX или MAIL или GMAIL. Программа работает только с этими почтовыми сервисами<br> </li>
  <li>Логин в вашей электронной почте <br></li>
  <li>Пароль от вашей электронной почты. Рекомендуется использовать <b>пароли приложений</b>. Подробная информация ниже <br></li>
   <li>Значение порт менять не нужно. Оно вынесено в файл настроек исключительно на случай изменений в работе почтовых сервисов<br></li>
   <li>Ваш телеграм токен. Подробная <b>инструкция ниже</b><br></li>
   <li>ID вашего чата. Информация, как его получить, также прикреплена в <b>инструкции ниже</b><br></li>
  </ul>
3 Сохраните изменения и закройте файл Config.properties. Запустите программу (jar или exe файл).<br>
4 Рядом с программой появится папка с логами. Для теста можете отправить на свою почту письмо и посмотреть, придет ли сообщение в телеграме. В случае ошибок можно посмотреть файлы с логами<br>
5 Пока программа запущена, тексты всех новых писем, приходящих вам в почту, будут пересылаться в вашу группу в телеграме
<br>
<br>
<h3> Пароли приложений: </h3>
У всех почтовых сервисов, с которыми работает программа, предусмотрен отдельный вход для почтовых приложений. Это сделано в целях безопасности (доступ не к аккаунту целиком с облаком, деньгами и другими данными, а только к почте) и удобства (почтовой программе нужно будет загружать меньше данных + уходим от проблемы двухфакторной аутентификации). Рекомендуется использовать именно пароли приложений для работы этой программы.<br>
1 Введите в поиск свой почтовый сервис + "пароли приложений". Например, "mail.ru пароли приложений"<br>
2 Скорее всего, вы увидите ссылки на нужные настройки или страницу с подробной инструкцией. <br>
3 Создайте свой пароль, вписав любое удобное для вас название приложения. Полученный пароль вставьте в файл настроек программы.<br>
4 Иногда нужно включать в настройках почтового сервиса работу с IMAP. На момент написания инструкции это требуется только у Gmail почты. Это также делается через настройки, буквально в несколько кликов<br>
<br>
<br>
<h3> Как получить Telegram Token: </h3>
Для работы программы необходимо наличие телеграм токена. Получить его довольно легко. Сделайте следующее:<br>
1 Введите в поиск телеграма @BotFather. Нажмите start и получите список команд бота<br>
2 Выберите или введите вручную команду /newbot<br>
3 Введите имя и username вашего бота. Username нужен для поиска бота внутри телеграма, поэтому его необходимо сделать уникальным и добавить в конце .bot<br>
4 Вы получите сообщение со ссылкой на вашего бота вида t.me/bot_username. Сохраните себе значение токена, внесите его в файл настроек программы<br>
5 Также вы увидите список команд бота, возможности для его настройки (установить картинку, описание бота и тд.). В рамках этой программы нам не нужен этот функционал<br>
<br>
<br>
<h3> Как узнать id своего чата: </h3>
Программа будет пересылать письма с e-mail в ваш чат в телеграме, и для этого ей нужен id этого чата. Вот простые шаги, как его получить:<br>
  1 Найдите в поиске @username_to_id_bot и добавьте его в нужную телеграм группу<br>
  2 Добавленный бот пришлет id вашей группы. Id часто начинается с минуса, не пугайтесь, это нормально. Внесите этот id в файл настроек, прямо так, с минусом<br>
  3 Теперь вы можете удалить бота из своей группы.<br>
  3 Ваша группа может стать супергруппой. Вы получите оповещение об этом. Если такое произойдет, получите id группы еще раз. После этого никаких изменений уже не будет.<br>
  <hr>
  <big>По вопросам изменения или улучшения программы можете писать мне в телеграм <b>@Mptimch</b> </big>
<br>
<br>
<br>
<br>
<br>
<br>
  <hr>
  <h1> Email-to-Telegram </h1>
<h2>Mini console app to send mails from Email to Telegram. You will need telegram token only </h2>
<br>
You can start the program, downloading jar or exe file. Just get this file to your PC and put file <b>config.properties</b> in the same folder.<br>
You can open config.properties with notepad and insert there your mail service, login, password, telegram token and telegram chat id.<br>
So, then you can start jar or exe application, and all your new emails will be sent to your telegram chat! It is very easy!<br>
<br>
<h3>Some extra advices:</h3>
<b>Don't forget to make all settings in cnfig.properties</b>!!!<br>
For best working, make a special password for this application. Do not use login and password, that you use in browser. Find an "application passwords" setting in your email service<br>
Enable the Imap setting in your email service. It's easy too<br>
You should create a new group in telegram and add your telegram bot in this group. If you didn't add the bot, no messages will be sended<br>
<br>
<h4>Getting Telegram token is easy: </h4>
- Enter @BotFather in the search tab and choose the relevant bot.<br>
- Click Start to activate the BotFather bot. You will receive a list of commands to manage the bots.<br>
- Choose or type the /newbot command and click Send<br>
- Choose a name for your bot. Next, choose a username for your bot. The bot can be found by its username in the search bar. The username must be unique and end with the word “bot.”<br>
- After you choose a suitable name for your bot, the bot will be created<br>
- You will receive a message with a link to your bot t.me/<bot_username>, recommendations to set up a profile picture, description of the profile, and a list of commands to manage your new bot.<br>
Copy the token value.<br>
<br>
<h4>Getting your chat id is easy! </h4>
Add <b>@username_to_id_bot</b> in your telegram group, and this bot will send you group id <br>
<b>Warning </b>! Your group can become a supergroup. You will get a notification about it. After this, your group's id will change. Get it again!<br>
<br>
<br>
You can write me to Telegram <b>@Mptimch</b> with suggestions for improving the program.
