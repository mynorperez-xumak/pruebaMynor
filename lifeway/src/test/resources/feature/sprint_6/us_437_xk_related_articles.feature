Feature: US-437 XK Related Articles
    This component will show a list of articles, related to the current one through tagging, this means that the component will show the most recent articles with the same tags or at least one.
    The list of articles has thumbnails on each, the thumbnails are optional, the rendering with and without the thumbnail should be like the screenshot

   @startRelatedArticles
   Scenario: Manual mode enabled
      Given the Manual Mode checkbox is set
      When the component related articles is rendered
      Then the configuration should override design configuration
      And the Image should be shown regarding if the specified article has or not thumbnail image configured
      And the component should be rendered as the screenshot

   Scenario: The component is rendered
      Given a Related Articles component
      When the component related articles is rendered
      Then it will show a list of Amount articles 
      And if the article has a thumbnail, it will be shown
      And the article title will be shown
      And the title of the article will be a link to the article's page
      And the summary of the article will be shown under the article title