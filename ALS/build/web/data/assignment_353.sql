-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 03, 2013 at 10:05 PM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `als`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE IF NOT EXISTS `answers` (
  `ans_id` int(11) NOT NULL AUTO_INCREMENT,
  `answers` varchar(45) DEFAULT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`ans_id`),
  KEY `fk_Answers_Questions_idx` (`question_id`),
  KEY `ans_id` (`ans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `assignments`
--

CREATE TABLE IF NOT EXISTS `assignments` (
  `assignment_id` int(11) NOT NULL AUTO_INCREMENT,
  `assign_name` varchar(45) DEFAULT NULL,
  `assign_statement` varchar(3000) NOT NULL,
  `assign_type` varchar(45) DEFAULT NULL,
  `total_marks` float DEFAULT NULL,
  `time_due` varchar(55) DEFAULT NULL,
  `time-modified` varchar(55) DEFAULT NULL,
  `course_id` int(11) NOT NULL,
  `attachment` varchar(300) NOT NULL,
  PRIMARY KEY (`assignment_id`),
  KEY `fk_Assignments_Courses1_idx` (`course_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `assignments`
--

INSERT INTO `assignments` (`assignment_id`, `assign_name`, `assign_statement`, `assign_type`, `total_marks`, `time_due`, `time-modified`, `course_id`, `attachment`) VALUES
(1, 'test', 'bla bla bla', NULL, 50, 'Thu Apr 18 00:00:00 PKT 2013', 'N/A', 2, 'N/A'),
(2, 'test', 'bla bla bla', NULL, 50, 'Thu Apr 18 00:00:00 PKT 2013', 'N/A', 2, 'N/A'),
(3, '3912893812098', '32094092348093248093284098320948', NULL, 5232, 'Wed Apr 10 00:00:00 PKT 2013', 'N/A', 3, 'N/A');

-- --------------------------------------------------------

--
-- Table structure for table `assign_result`
--

CREATE TABLE IF NOT EXISTS `assign_result` (
  `assign_submission_id` int(11) NOT NULL,
  `marks_obtained` int(11) DEFAULT NULL,
  PRIMARY KEY (`assign_submission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `assign_submission`
--

CREATE TABLE IF NOT EXISTS `assign_submission` (
  `assign_submission_id` int(11) NOT NULL AUTO_INCREMENT,
  `assignment_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `attachment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`assign_submission_id`),
  KEY `fk_assign_submission_Assignments1_idx` (`assignment_id`),
  KEY `fk_assign_submission_Student1_idx` (`student_id`),
  KEY `fk_assign_submission_Attachment1_idx` (`attachment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE IF NOT EXISTS `attachment` (
  `attachment_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `path` varchar(45) DEFAULT NULL,
  `size` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`attachment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `chat`
--

CREATE TABLE IF NOT EXISTS `chat` (
  `chat_id` int(11) NOT NULL AUTO_INCREMENT,
  `chat_time` time DEFAULT NULL,
  `chat_topic` char(1) DEFAULT NULL,
  PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `chat_message`
--

CREATE TABLE IF NOT EXISTS `chat_message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `chat_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `fk_chat_message_chat1_idx` (`chat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `course_name` varchar(45) DEFAULT NULL,
  `pre-requisite_id` varchar(45) DEFAULT NULL,
  `co-requisite_id` varchar(45) DEFAULT NULL,
  `credit_hrs` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `course_id` varchar(45) NOT NULL,
  `program_id` int(11) NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`course_name`, `pre-requisite_id`, `co-requisite_id`, `credit_hrs`, `category_id`, `course_id`, `program_id`) VALUES
('Web Engineering', '2', '-1', 4, 1, 'CS105', 1),
('Java', '3', '-1', 4, 1, 'CS106', 1),
('C++', '-1', '-1', 4, 1, 'CS107', 1);

-- --------------------------------------------------------

--
-- Table structure for table `course_category`
--

CREATE TABLE IF NOT EXISTS `course_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) DEFAULT NULL,
  `description` char(1) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `course_division`
--

CREATE TABLE IF NOT EXISTS `course_division` (
  `division_id` int(11) NOT NULL AUTO_INCREMENT,
  `section_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`division_id`),
  KEY `fk_course_division_course_section1_idx` (`section_id`),
  KEY `fk_course_division_Courses1_idx` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `course_section`
--

CREATE TABLE IF NOT EXISTS `course_section` (
  `section_id` int(11) NOT NULL AUTO_INCREMENT,
  `section_name` varchar(45) DEFAULT NULL,
  `description` char(1) DEFAULT NULL,
  PRIMARY KEY (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `couse_topic`
--

CREATE TABLE IF NOT EXISTS `couse_topic` (
  `topic id` int(11) NOT NULL,
  `topic name` varchar(55) DEFAULT NULL,
  `pre-requisite topic_id` int(11) NOT NULL,
  `attachment_id` varchar(45) NOT NULL,
  PRIMARY KEY (`topic id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(45) DEFAULT NULL,
  `institute_id` int(11) NOT NULL,
  PRIMARY KEY (`department_id`),
  KEY `fk_department_Institute1_idx` (`institute_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`department_id`, `department_name`, `institute_id`) VALUES
(1, 'Computer Science', 1);

-- --------------------------------------------------------

--
-- Table structure for table `finalreport`
--

CREATE TABLE IF NOT EXISTS `finalreport` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `course_id` varchar(55) NOT NULL,
  `quiz_submission_id` int(11) NOT NULL,
  `assinment_submission_id` int(11) NOT NULL,
  `grade_id` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_FinalReport_grade1_idx` (`grade_id`),
  KEY `fk_FinalReport_Courses1_idx` (`course_id`),
  KEY `fk_FinalReport_Student1_idx` (`student_id`),
  KEY `fk_FinalReport_assign_submission1_idx` (`assinment_submission_id`),
  KEY `fk_FinalReport_quiz_submission1_idx` (`quiz_submission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `forum`
--

CREATE TABLE IF NOT EXISTS `forum` (
  `forum_id` int(11) NOT NULL AUTO_INCREMENT,
  `forum_name` varchar(45) DEFAULT NULL,
  `course_id` varchar(100) NOT NULL,
  `date_created` date NOT NULL,
  `Views` int(11) NOT NULL,
  `comments` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `details` varchar(1000) NOT NULL,
  PRIMARY KEY (`forum_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `forum`
--

INSERT INTO `forum` (`forum_id`, `forum_name`, `course_id`, `date_created`, `Views`, `comments`, `created_by`, `details`) VALUES
(1, 'ali', 'CS107', '2013-04-16', 0, 0, 1, ''),
(2, 'my java', 'CS106', '2013-04-16', 3, 5, 1, ''),
(8, 'test topic', 'CS105', '2013-04-16', 0, 0, 1, ''),
(9, 'php sessions', 'CS105', '2013-04-16', 0, 0, 1, ''),
(12, '0900', 'CS106', '2013-04-18', 0, 0, 1, ''),
(13, 'for loop', 'CS106', '2013-05-01', 44, 28, 1, 'how to convert for loop into while loop?');

-- --------------------------------------------------------

--
-- Table structure for table `forum_post`
--

CREATE TABLE IF NOT EXISTS `forum_post` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Forum_ID` int(11) NOT NULL,
  `Post_Text` varchar(1000) NOT NULL,
  `Post_Date` date NOT NULL,
  `Post_Time` time NOT NULL,
  `User_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Dumping data for table `forum_post`
--

INSERT INTO `forum_post` (`ID`, `Forum_ID`, `Post_Text`, `Post_Date`, `Post_Time`, `User_ID`) VALUES
(1, 13, 'hi, i also want to know??', '2013-05-01', '16:30:38', 1),
(2, 13, 'tell me plz', '2013-05-01', '16:31:28', 1),
(3, 13, 'i am waiting....', '2013-05-01', '16:56:07', 1),
(4, 13, 'yo', '2013-05-01', '17:06:05', 1),
(5, 13, 'yo', '2013-05-01', '17:09:08', 1),
(6, 13, 'yo', '2013-05-01', '17:09:27', 1),
(7, 13, 'dasd\r\ndasdas\r\nasdasd\r\nasdas\r\n', '2013-05-01', '17:11:17', 1),
(8, 13, 'hi <br />\r\nhow are you?', '2013-05-01', '17:12:10', 1),
(9, 13, 'open browser', '2013-05-01', '17:29:50', 1),
(10, 13, 'abc', '2013-05-01', '17:34:50', 1),
(11, 13, '123', '2013-05-01', '17:39:57', 1),
(12, 13, '333', '2013-05-01', '17:44:00', 1),
(13, 13, '234', '2013-05-01', '17:56:12', 1),
(14, 13, 'dadasd', '2013-05-01', '17:58:03', 1),
(15, 13, '339', '2013-05-01', '20:12:40', 1),
(16, 13, '2313', '2013-05-01', '20:24:51', 1),
(17, 13, 'addasd', '2013-05-01', '20:28:30', 1),
(18, 0, 'dd3r32f', '2013-05-01', '20:31:07', 1),
(19, 13, 'last comment', '2013-05-01', '20:35:10', 1),
(20, 13, 'we', '2013-05-01', '20:36:35', 1),
(21, 13, 'love IK', '2013-05-01', '20:37:23', 1),
(22, 13, 'PTI', '2013-05-01', '20:39:05', 1),
(23, 13, 'sdasd', '2013-05-01', '20:40:27', 1),
(24, 13, 'super man', '2013-05-01', '20:42:24', 1),
(25, 13, 'test 11', '2013-05-01', '20:45:22', 1),
(26, 13, 'abc', '2013-05-01', '20:48:05', 1),
(27, 13, 'cehck 88', '2013-05-01', '20:50:21', 1),
(28, 13, 'ptii ik', '2013-05-02', '00:23:09', 1),
(29, 13, 'hi', '2013-05-02', '13:32:41', 1);

-- --------------------------------------------------------

--
-- Table structure for table `grade`
--

CREATE TABLE IF NOT EXISTS `grade` (
  `grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `letter_grade` char(1) DEFAULT NULL,
  `range` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `help`
--

CREATE TABLE IF NOT EXISTS `help` (
  `help_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `topic` varchar(45) DEFAULT NULL,
  `link_id` int(11) NOT NULL,
  `scroll_div` varchar(45) DEFAULT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`help_id`),
  KEY `fk_help_Courses1_idx` (`course_id`),
  KEY `fk_help_Student1_idx` (`student_id`),
  KEY `fk_help_links1_idx` (`link_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `institute`
--

CREATE TABLE IF NOT EXISTS `institute` (
  `institute_id` int(11) NOT NULL AUTO_INCREMENT,
  `institute_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`institute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `lesson`
--

CREATE TABLE IF NOT EXISTS `lesson` (
  `lesson_id` int(11) NOT NULL AUTO_INCREMENT,
  `lesson_name` varchar(55) DEFAULT NULL,
  `max-attempts` int(11) DEFAULT NULL,
  `helper_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`lesson_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `lesson-content`
--

CREATE TABLE IF NOT EXISTS `lesson-content` (
  `lesson-content_id` int(11) NOT NULL AUTO_INCREMENT,
  `lesson_id` int(11) NOT NULL,
  `lesson-pre-requisite_id` int(11) NOT NULL,
  `couse_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`lesson-content_id`),
  KEY `fk_lesson-content_Lesson1_idx` (`lesson_id`),
  KEY `fk_lesson-content_Courses1_idx` (`couse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `lesson_timming`
--

CREATE TABLE IF NOT EXISTS `lesson_timming` (
  `lesson-timing_id` int(11) NOT NULL AUTO_INCREMENT,
  `lesson_id` varchar(45) NOT NULL,
  `started_time` varchar(55) DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  PRIMARY KEY (`lesson-timing_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `links`
--

CREATE TABLE IF NOT EXISTS `links` (
  `link_id` int(11) NOT NULL AUTO_INCREMENT,
  `link_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`link_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `mcqs`
--

CREATE TABLE IF NOT EXISTS `mcqs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `answers` varchar(45) DEFAULT NULL,
  `correct_answer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_MCQS_Questions1_idx` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE IF NOT EXISTS `notifications` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` varchar(50) NOT NULL,
  `details` varchar(100) NOT NULL,
  `ndate` date NOT NULL,
  `Student_id` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`ID`, `course_id`, `details`, `ndate`, `Student_id`) VALUES
(1, 'CS105', 'Submitted Assignment of Database', '2013-04-10', 2),
(2, 'CS105', 'Attended Quiz of Data Mining', '2013-04-09', 2),
(3, 'CS105', 'testing', '2013-05-23', 2),
(4, 'CS105', 'its working', '2013-05-16', 2);

-- --------------------------------------------------------

--
-- Table structure for table `program`
--

CREATE TABLE IF NOT EXISTS `program` (
  `program_id` int(11) NOT NULL AUTO_INCREMENT,
  `program_name` varchar(100) NOT NULL,
  `department_id` int(11) NOT NULL,
  PRIMARY KEY (`program_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `program`
--

INSERT INTO `program` (`program_id`, `program_name`, `department_id`) VALUES
(1, 'BS(CS)', 1);

-- --------------------------------------------------------

--
-- Table structure for table `questionlevel`
--

CREATE TABLE IF NOT EXISTS `questionlevel` (
  `level_id` int(11) NOT NULL AUTO_INCREMENT,
  `diificulty level` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE IF NOT EXISTS `questions` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `level_id` int(11) NOT NULL,
  `question` varchar(45) DEFAULT NULL,
  `question_text` varchar(45) DEFAULT NULL,
  `time_created` time DEFAULT NULL,
  `marks` int(11) DEFAULT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `fk_Questions_QuestionLevel1_idx` (`level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `questiontype`
--

CREATE TABLE IF NOT EXISTS `questiontype` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

CREATE TABLE IF NOT EXISTS `quiz` (
  `quiz_id` int(11) NOT NULL AUTO_INCREMENT,
  `quiz_name` varchar(45) DEFAULT NULL,
  `time_open` time DEFAULT NULL,
  `time_close` time DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`quiz_id`),
  KEY `fk_quiz_Courses1_idx` (`course_id`),
  KEY `fk_quiz_Questions1_idx` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `quiz_result`
--

CREATE TABLE IF NOT EXISTS `quiz_result` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `quiz_submission_id` int(11) NOT NULL,
  `totak_marks` int(11) DEFAULT NULL,
  `marks_obtained` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_quiz_result_quiz_submission1` (`quiz_submission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `quiz_submission`
--

CREATE TABLE IF NOT EXISTS `quiz_submission` (
  `quiz_submission_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `quiz_id` int(11) NOT NULL,
  PRIMARY KEY (`quiz_submission_id`),
  KEY `fk_quiz_submission_quiz1_idx` (`quiz_id`),
  KEY `fk_quiz_submission_Student1_idx` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `interest` varchar(45) DEFAULT NULL,
  `background` varchar(45) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `qualification` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `student_registration`
--

CREATE TABLE IF NOT EXISTS `student_registration` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `course_id` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_student_registration_Student1_idx` (`student_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `student_registration`
--

INSERT INTO `student_registration` (`ID`, `student_id`, `course_id`) VALUES
(1, 1, 'CS105'),
(2, 1, 'CS105');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE IF NOT EXISTS `teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `qualification` varchar(45) DEFAULT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `teacher_course`
--

CREATE TABLE IF NOT EXISTS `teacher_course` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` int(11) NOT NULL,
  `course_id` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_teacher_course_Teacher1_idx` (`teacher_id`),
  KEY `fk_teacher_course_Courses1_idx` (`course_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `teacher_course`
--

INSERT INTO `teacher_course` (`ID`, `teacher_id`, `course_id`) VALUES
(1, 1, 'CS105'),
(2, 3, 'CS105'),
(3, 1, 'CS106'),
(4, 1, 'CS107'),
(5, 4, 'CS105'),
(6, 2, 'CS106');

-- --------------------------------------------------------

--
-- Table structure for table `todo`
--

CREATE TABLE IF NOT EXISTS `todo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `todo_title` varchar(1000) NOT NULL,
  `todo_date` date NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `todo`
--

INSERT INTO `todo` (`ID`, `user_id`, `todo_title`, `todo_date`) VALUES
(1, 1, 'Surprize Quiz', '2013-05-15'),
(2, 1, 'Database Assignment', '2013-05-17'),
(3, 1, 'fhfh', '2013-05-08'),
(4, 1, 'check quiz', '2013-05-30'),
(5, 1, 'upload reults', '2013-05-08'),
(6, 1, 'gchdfh', '2013-05-16'),
(7, 1, 'dffd', '2013-05-04');

-- --------------------------------------------------------

--
-- Table structure for table `truefalse`
--

CREATE TABLE IF NOT EXISTS `truefalse` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `true_ans` varchar(45) DEFAULT NULL,
  `false_ans` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_TrueFalse_Questions1_idx` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `phone_number` varchar(111) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `hobby` varchar(45) DEFAULT NULL,
  `language` varchar(45) DEFAULT NULL,
  `picture` longblob,
  `gender` varchar(45) DEFAULT NULL,
  `user_id` varchar(45) NOT NULL DEFAULT '',
  `user_type` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`first_name`, `last_name`, `email`, `password`, `phone_number`, `address`, `user_name`, `dob`, `hobby`, `language`, `picture`, `gender`, `user_id`, `user_type`) VALUES
('Ali', 'Nawaz', 'ali@gmail.com', '123', '0', 'none', 'ali', '0000-00-00', 'none', 'englidh', 0xffd8ffe000104a46494600010200000100010000ffdb004300080606070605080707070909080a0c140d0c0b0b0c1912130f141d1a1f1e1d1a1c1c20242e2720222c231c1c2837292c30313434341f27393d38323c2e333432ffdb0043010909090c0b0c, 'male', '1', 'teacher'),
('Waleed', 'Imtiaz', 'Waleed@gmail.com', '123', 'none', 'none', 'waleed', '2013-04-03', 'none', 'english', NULL, 'Male', '2', 'student'),
('Imran', 'Raza', 'imran.raza@gmail.com', '123', 'none', 'none', 'imran', '2013-05-16', 'none', 'English', NULL, 'Male', '3', 'teacher'),
('hey', 'abeera', 'hey_abira@hotmail.com', '123', 'none', 'none', 'abeera', '2013-05-16', 'none', 'none', NULL, 'Female', '4', 'admin');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `fk_Answers_Questions` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
