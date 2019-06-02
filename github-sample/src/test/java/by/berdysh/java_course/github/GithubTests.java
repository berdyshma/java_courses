package by.berdysh.java_course.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;


public class GithubTests {

	@Test

	public void testCommits() throws IOException {
		Github github = new RtGithub("97671c75c4ba400859ce3c9849613f6e2df85633");
		RepoCommits commits = github.repos().get(new Coordinates.Simple("berdyshma", "java_courses")).commits();
		for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
			System.out.println(new RepoCommit.Smart(commit).message());
		}

	}
}

