-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 03, 2013 at 09:38 PM
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
  `assign_type` varchar(45) DEFAULT NULL,
  `total-marks` float DEFAULT NULL,
  `time-due` varchar(55) DEFAULT NULL,
  `time-modified` varchar(55) DEFAULT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`assignment_id`),
  KEY `fk_Assignments_Courses1_idx` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
  `max-size` int(11) DEFAULT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`forum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `forumpost`
--

CREATE TABLE IF NOT EXISTS `forumpost` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `discussion_id` int(11) NOT NULL,
  `time_created` time DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `attachment_id` int(11) NOT NULL,
  `subject` varchar(45) DEFAULT NULL,
  `message` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `fk_ForumPost_forum_discussion1_idx` (`discussion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `forum_discussion`
--

CREATE TABLE IF NOT EXISTS `forum_discussion` (
  `discussion_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `forum_id` int(11) NOT NULL,
  PRIMARY KEY (`discussion_id`),
  KEY `fk_forum_discussion_forum1_idx` (`forum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
  `first name` varchar(50) DEFAULT NULL,
  `last name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `phone number` int(11) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `dob` varchar(45) DEFAULT NULL,
  `hobby` varchar(45) DEFAULT NULL,
  `language` varchar(45) DEFAULT NULL,
  `picture` blob,
  `gender` varchar(45) DEFAULT NULL,
  `user_id` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
