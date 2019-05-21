package pku.course.java;

import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class App
{

    public static final String serverUrl = "localhost:1433";
    public static final String databaseName = "DatabaseCourse";
    public static final String username = "SA";
    public static final String password = "<YourStrong!Passw0rd>"; //"975481DingDing";
    public static final int tokenLength = 30;

    public static final String connectionString =
        "jdbc:sqlserver://" + serverUrl + ";databaseName=" + databaseName +
        ";user=" + username + ";password=" + password;

    public static void main(String[] args)
    {
    }

    private static String _randomString()
    {
        return RandomStringUtils.randomAlphanumeric(tokenLength);
    }

    private static BigDecimal _verifyToken(String token)
    {
        BigDecimal result = null;

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "SELECT [ACCOUNT_ID] FROM [SESSION_IDENTIFIERS] " +
                "WHERE [SESSION_TOKEN] = ?";
            PreparedStatement statement = connection.prepareStatement(sqlCode);
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                result = resultSet.getBigDecimal(1);
            }
        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result;
    }

    public static String createNewAccount(String emailAddress,
        String username, String password)
    {
        Boolean result = false;

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "{? = call [dbo].[Create_Account] (?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(sqlCode);
            statement.registerOutParameter(1, java.sql.Types.BIT);
            statement.setString(2, emailAddress);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.execute();

            result = statement.getBoolean(1);

        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        if (result) return "True";
        else return "False";
    }

    public static String login(String username, String hashedPassword)
    {
        Boolean result = false;
        String token = _randomString();

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "{? = call [dbo].[Identification_Authentication_Authorization] (?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(sqlCode);
            statement.registerOutParameter(1, java.sql.Types.BIT);
            statement.setString(2, username);
            statement.setString(3, hashedPassword);
            statement.setString(4, token);
            statement.execute();

            result = statement.getBoolean(1);
        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }
        if (result)
        {
            return token;
        }
        else
        {
            return "False";
        }
    }

    public static String fetchTopics(String token)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        JSONArray result = new JSONArray();

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "SELECT [TOPIC_ID], [TOPIC_TITLE], [TOPIC_DESCRIPTION] " +
                "FROM [dbo].[TOPICS]";
            PreparedStatement statement = connection.prepareStatement(sqlCode);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("topic_id", resultSet.getBigDecimal(1).toString());
                jsonObject.put("topic_title", resultSet.getString(2));
                jsonObject.put("topic_description", resultSet.getString(3));
                result.add(jsonObject);
            }

        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result.toJSONString();
    }

    public static String fetchTopicsFollowed(String token)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        JSONArray result = new JSONArray();

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "{call [dbo].[Get_Topics_Followed] (?)}";
            CallableStatement statement = connection.prepareCall(sqlCode);
            statement.setBigDecimal(1, accountId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next())
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("topic_id", resultSet.getBigDecimal(1).toString());
                jsonObject.put("topic_title", resultSet.getString(2));
                jsonObject.put("topic_description", resultSet.getString(3));
                result.add(jsonObject);
            }

        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result.toJSONString();
    }

    public static String fetchQuestion(String token, String questionId)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        JSONObject result = new JSONObject();

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "SELECT t1.[QUESTION_ID], t2.[TOPIC_ID], t2.[TOPIC_TITLE], t1.[QUESTION_TITLE], t1.[QUESTION_TEXT], t1.[QUESTION_DATE___TIME] " +
                "FROM [QUESTIONS] AS t1 JOIN [TOPICS] AS t2 " +
                "ON t1.[TOPIC_ID] = t2.[TOPIC_ID] WHERE t1.[QUESTION_ID] = ?";
            PreparedStatement statement = connection.prepareStatement(sqlCode);
            statement.setBigDecimal(1, new BigDecimal(questionId));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                result.put("question_id", resultSet.getBigDecimal(1).toString());
                result.put("topic_id", resultSet.getBigDecimal(2).toString());
                result.put("topic_title", resultSet.getString(3));
                result.put("question_title", resultSet.getString(4));
                result.put("question_text", resultSet.getString(5));
            }
            else return "False";

        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result.toJSONString();
    }

    public static String fetchQuestionsFollowed(String token)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        JSONArray result = new JSONArray();

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "{call [dbo].[Get_Questions_Followed] (?)}";
            CallableStatement statement = connection.prepareCall(sqlCode);
            statement.setBigDecimal(1, accountId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next())
            {
                result.add(resultSet.getBigDecimal(1).toString());
            }

        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result.toJSONString();
    }

    public static String fetchMyQuestions(String token)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        JSONArray result = new JSONArray();

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "SELECT [QUESTION_ID] FROM [QUESTIONS] " +
                "WHERE [ACCOUNT_ID] = ?";
            PreparedStatement statement = connection.prepareStatement(sqlCode);
            statement.setBigDecimal(1, accountId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                result.add(resultSet.getBigDecimal(1).toString());
            }

        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result.toJSONString();
    }

    public static String fetchQuestionsUnderTopic(String token, String topicId)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        JSONArray result = new JSONArray();

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "SELECT [QUESTION_ID] FROM [QUESTIONS] " +
                "WHERE [TOPIC_ID] = ?";
            PreparedStatement statement = connection.prepareStatement(sqlCode);
            statement.setBigDecimal(1, new BigDecimal(topicId));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                result.add(resultSet.getBigDecimal(1).toString());
            }

        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result.toJSONString();
    }

    public static String createQuestion(String token, String topicId,
        String questionTitle, String questionText)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        String result = null;

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            Timestamp timestamp =
                new Timestamp(Calendar.getInstance().getTimeInMillis());
            String sqlCode1 =
                "{call [dbo].[Create_Question] (?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(sqlCode1);
            statement.setBigDecimal(1, accountId);
            statement.setBigDecimal(2, new BigDecimal(topicId));
            statement.setString(3, questionTitle);
            statement.setString(4, questionText);
            statement.setTimestamp(5, timestamp);
            statement.execute();

            String sqlCode2 =
                "SELECT [QUESTION_ID] FROM [QUESTIONS] " +
                "WHERE [ACCOUNT_ID] = ? AND " +
                "[TOPIC_ID] = ? AND " +
                "[QUESTION_TITLE] = ? AND " +
                "[QUESTION_TEXT] = ? AND " +
                "[QUESTION_DATE___TIME] = z/";
            PreparedStatement statement2 =
                connection.prepareStatement(sqlCode2);
            statement.setBigDecimal(1, accountId);
            statement.setBigDecimal(2, new BigDecimal(topicId));
            statement.setString(3, questionTitle);
            statement.setString(4, questionText);
            statement.setTimestamp(5, timestamp);
            ResultSet resultSet = statement2.executeQuery();
            resultSet.next();
            result = resultSet.getBigDecimal(1).toString();

        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result;
    }

    public static String writeAnswer(String token, String questionId,
        String answerText)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "{call [dbo].[Create_Question] (?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(sqlCode);
            statement.setBigDecimal(1, new BigDecimal(questionId));
            statement.setBigDecimal(2, accountId);
            statement.setString(3, answerText);
            statement.execute();
        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return "True";
    }

    public static String fetchAnswer(String token, String answerId)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        JSONObject result = new JSONObject();

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "SELECT [QUESTION_ID], [ANSWER_ID], [ACCOUNT_ID], " +
                "[ANSWER_TEXT], [ANSWER_DATE___TIME], [ACCEPTED] " +
                "FROM [ANSWERS] WHERE [ANSWER_ID] = ?;";
            PreparedStatement statement = connection.prepareStatement(sqlCode);
            statement.setBigDecimal(1, new BigDecimal(answerId));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                result.put("question_id", resultSet.getBigDecimal(1).toString());
                result.put("answer_id", resultSet.getBigDecimal(2).toString());
                result.put("account_id", resultSet.getBigDecimal(3).toString());
                result.put("answer_text", resultSet.getString(4));
            }
            else return "False";

        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result.toJSONString();
    }

    public static String fetchMyAnswers(String token)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        JSONArray result = new JSONArray();

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "SELECT [ANSWER_ID] FROM [ANSWERS] " +
                "WHERE [ACCOUNT_ID] = ?";
            PreparedStatement statement = connection.prepareStatement(sqlCode);
            statement.setBigDecimal(1, accountId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                result.add(resultSet.getBigDecimal(1).toString());
            }

        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result.toJSONString();
    }

    public static String createTopic(String token, String topicTitle,
        String topicDescription)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "{call [dbo].[Create_Topic] (?, ?)}";
            CallableStatement statement = connection.prepareCall(sqlCode);
            statement.setString(1, topicTitle);
            statement.setString(2, topicDescription);
            statement.execute();
        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return "True";
    }

    public static String modifyPassword(String token, String oldPassword,
        String newPassword)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "{call [dbo].[Update_Password] (?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(sqlCode);
            statement.setBigDecimal(1, accountId);
            statement.setString(2, oldPassword);
            statement.setString(3, newPassword);
            statement.execute();
        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return "True";
    }

    public static String followTopic(String token, String topicId,
        String followOrUnfollow)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "{call [dbo].[Follow_Topic] (?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(sqlCode);
            statement.setBigDecimal(1, new BigDecimal(topicId));
            statement.setBigDecimal(2, accountId);
            if (followOrUnfollow.equals(followOrUnfollow))
            {
                statement.setBoolean(3, true);
            }
            else statement.setBoolean(3, false);

            statement.execute();
        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return "True";
    }

    public static String followQuestion(String token, String questionId,
        String followOrUnfollow)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "{call [dbo].[Follow_Question] (?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(sqlCode);
            statement.setBigDecimal(1, new BigDecimal(questionId));
            statement.setBigDecimal(2, accountId);
            if (followOrUnfollow.equals(followOrUnfollow))
            {
                statement.setBoolean(3, true);
            }
            else statement.setBoolean(3, false);

            statement.execute();
        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return "True";
    }

    public static String getAnswersOfQuestion(String token, String questionId)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        JSONArray result = new JSONArray();

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "SELECT [ANSWER_ID] FROM [ANSWERS] " +
                "WHERE [QUESTION_ID] = ?";
            PreparedStatement statement = connection.prepareStatement(sqlCode);
            statement.setBigDecimal(1, new BigDecimal(questionId));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                result.add(resultSet.getBigDecimal(1).toString());
            }

        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result.toJSONString();
    }

    public static String searchQuestions(String token, String searchString)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        JSONArray result = new JSONArray();
        String[] keywords = searchString.split("\\s+");

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode1 =
                "if exists (select 1 " +
                "from  sysobjects " +
                "where  id = object_id('USER_TEMP_T') " +
                "and   type = 'U') " +
                "drop table USER_TEMP_T;";
            String sqlCode2 =
                "CREATE TABLE [dbo].[USER_TEMP_T] ( " +
                    "KEYWORD NVARCHAR(MAX) NOT NULL, " +
                    "INCLUDING bit NOT NULL";
            String sqlCode3 =
                "INSERT INTO [dbo].[USER_TEMP_T] (KEYWORD, INCLUDING) " +
                "VALUES (?, ?)";
            String sqlCode4 =
                "DECLARE @query AS SEARCH_QUERY; " +
                "INSERT INTO @query ([KEYWORD], [INCLUDING]) " +
                    "SELECT [KEYWORD], [INCLUDING] " +
                    "FROM [dbo].[USER_TEMP_T]; " +
                "{call [dbo].[Search_Question] (@query)};";
            String sqlCode5 =
                "SELECT [ID] FROM [tttt_user]";
            String sqlCode6 =
                "if exists (select 1 " +
                "from  sysobjects " +
                "where  id = object_id('tttt_user') " +
                "and   type = 'U') " +
                "drop table tttt_user;";
            String sqlCode7 =
                "CREATE TABLE [dbo].[tttt_user] ( " +
                "KEYWORD NVARCHAR(MAX) NOT NULL, " +
                "INCLUDING bit NOT NULL";


            Statement statement = connection.createStatement();
            statement.addBatch(sqlCode1);
            statement.addBatch(sqlCode2);
            statement.addBatch(sqlCode6);
            statement.addBatch(sqlCode7);
            statement.executeBatch();

            PreparedStatement preparedStatement =
                connection.prepareStatement(sqlCode3);
            for (String keyword: keywords)
            {
                preparedStatement.setString(1, keyword);
                preparedStatement.setBoolean(2, true);
                preparedStatement.execute();
            }

            statement.execute(sqlCode4);

            ResultSet resultSet = statement.executeQuery(sqlCode5);

            while (resultSet.next())
            {
                result.add(resultSet.getBigDecimal(1).toString());
            }

            statement.executeUpdate(sqlCode1);
            statement.executeUpdate(sqlCode6);

        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result.toJSONString();
    }

    public static String searchTopics(String token, String searchString)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        JSONArray result = new JSONArray();
        String[] keywords = searchString.split("\\s+");

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode1 =
                "if exists (select 1 " +
                "from  sysobjects " +
                "where  id = object_id('USER_TEMP_S') " +
                "and   type = 'U') " +
                "drop table USER_TEMP_S;";
            String sqlCode2 =
                "CREATE TABLE [dbo].[USER_TEMP_S] ( " +
                    "KEYWORD NVARCHAR(MAX) NOT NULL, " +
                    "INCLUDING bit NOT NULL";
            String sqlCode3 =
                "INSERT INTO [dbo].[USER_TEMP_S] (KEYWORD, INCLUDING) " +
                "VALUES (?, ?)";
            String sqlCode4 =
                "DECLARE @query AS SEARCH_QUERY; " +
                "INSERT INTO @query ([KEYWORD], [INCLUDING]) " +
                    "SELECT [KEYWORD], [INCLUDING] " +
                    "FROM [dbo].[USER_TEMP_S]; " +
                "{call [dbo].[Search_Topic] (@query)};";
            String sqlCode5 =
                "SELECT [ID] FROM [ssss_user]";
            String sqlCode6 =
                "if exists (select 1 " +
                "from  sysobjects " +
                "where  id = object_id('ssss_user') " +
                "and   type = 'U') " +
                "drop table ssss_user;";
            String sqlCode7 =
                "CREATE TABLE [dbo].[ssss_user] ( " +
                "KEYWORD NVARCHAR(MAX) NOT NULL, " +
                "INCLUDING bit NOT NULL";


            Statement statement = connection.createStatement();
            statement.addBatch(sqlCode1);
            statement.addBatch(sqlCode2);
            statement.addBatch(sqlCode6);
            statement.addBatch(sqlCode7);
            statement.executeBatch();

            PreparedStatement preparedStatement =
                connection.prepareStatement(sqlCode3);
            for (String keyword: keywords)
            {
                preparedStatement.setString(1, keyword);
                preparedStatement.setBoolean(2, true);
                preparedStatement.execute();
            }

            statement.execute(sqlCode4);

            ResultSet resultSet = statement.executeQuery(sqlCode5);

            while (resultSet.next())
            {
                result.add(resultSet.getBigDecimal(1).toString());
            }

            statement.executeUpdate(sqlCode1);
            statement.executeUpdate(sqlCode6);

        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result.toJSONString();
    }

    public static String fetchTopicById(String token, String topicId)
    {
        BigDecimal accountId = _verifyToken(token);
        if (accountId == null)
        {
            return "False";
        }

        JSONObject result = new JSONObject();

        try
        (
            Connection connection =
                DriverManager.getConnection(connectionString)
        )
        {
            String sqlCode =
                "SELECT [TOPIC_TITLE], [TOPIC_DESCRIPTION] " +
                "FROM [TOPIC_ID] WHERE [TOPICS] = ?;";
            PreparedStatement statement = connection.prepareStatement(sqlCode);
            statement.setBigDecimal(1, new BigDecimal(topicId));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
            {
                result.put("topic_title", resultSet.getString(1));
                result.put("topic_description", resultSet.getString(2));
            }
            else return "False";

        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result.toJSONString();
    }

    public static String query(String jsonString)
    {
        String result = null;
        try
        {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(jsonString);

            switch ((String) jsonArray.get(0))
            {
                case "createNewAccount":
                    result = createNewAccount((String) jsonArray.get(1),
                        (String) jsonArray.get(2),
                        (String) jsonArray.get(3)
                    );
                    break;
                case "login":
                    result = login((String) jsonArray.get(1),
                        (String) jsonArray.get(2)
                    );
                    break;
                case "fetchTopics":
                    result = fetchTopics((String) jsonArray.get(1)
                    );
                    break;
                case "fetchTopicsFollowed":
                    result = fetchTopicsFollowed((String) jsonArray.get(1)
                    );
                    break;
                case "fetchQuestion":
                    result = fetchQuestion((String) jsonArray.get(1),
                        (String) jsonArray.get(2)
                    );
                    break;
                case "fetchQuestionsFollowed":
                    result = fetchQuestionsFollowed((String) jsonArray.get(1)
                    );
                    break;
                case "fetchMyQuestions":
                    result = fetchMyQuestions((String) jsonArray.get(1)
                    );
                    break;
                case "fetchQuestionsUnderTopic":
                    result = fetchQuestionsUnderTopic((String) jsonArray.get(1),
                        (String) jsonArray.get(2)
                    );
                    break;
                case "createQuestion":
                    result = createQuestion((String) jsonArray.get(1),
                        (String) jsonArray.get(2),
                        (String) jsonArray.get(3),
                        (String) jsonArray.get(4)
                    );
                    break;
                case "writeAnswer":
                    result = writeAnswer((String) jsonArray.get(1),
                        (String) jsonArray.get(2),
                        (String) jsonArray.get(3)
                    );
                    break;
                case "fetchAnswer":
                    result = fetchAnswer((String) jsonArray.get(1),
                        (String) jsonArray.get(2)
                    );
                    break;
                case "fetchMyAnswers":
                    result = fetchMyAnswers((String) jsonArray.get(1)
                    );
                    break;
                case "createTopic":
                    result = createTopic((String) jsonArray.get(1),
                        (String) jsonArray.get(2),
                        (String) jsonArray.get(3)
                    );
                    break;
                case "modifyPassword":
                    result = modifyPassword((String) jsonArray.get(1),
                        (String) jsonArray.get(2),
                        (String) jsonArray.get(3)
                    );
                    break;
                case "followTopic":
                    result = followTopic((String) jsonArray.get(1),
                        (String) jsonArray.get(2),
                        (String) jsonArray.get(3)
                    );
                    break;
                case "followQuestion":
                    result = followQuestion((String) jsonArray.get(1),
                        (String) jsonArray.get(2),
                        (String) jsonArray.get(3)
                    );
                    break;
                case "getAnswersOfQuestion":
                    result = getAnswersOfQuestion((String) jsonArray.get(1),
                        (String) jsonArray.get(2)
                    );
                    break;
                case "searchQuestions":
                    result = searchQuestions((String) jsonArray.get(1),
                        (String) jsonArray.get(2)
                    );
                    break;
                case "searchTopics":
                    result = searchTopics((String) jsonArray.get(1),
                        (String) jsonArray.get(2)
                    );
                    break;
                default:
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.println();
            e.printStackTrace();
        }

        return result;
    }
}