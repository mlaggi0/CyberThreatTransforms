/*
    FreeTransform, scripts that perform simple Maltego transformations.
    Copyright (C) 2017  Mladen Petr

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
	The script is called upon an arbitrary node.
	Parameter is node text.
	The scripts runs a search using twitter API and returns first 20 users that are related to
	the parameter. (It is possible to do 1000 results)
	Results are shown with attached link to twitter profile.
	If nothing is found, the user is properly notified.
*/

// @ExecutionModes({on_single_node="node_popup_scripting/FreeTransform/Twitter[addons.installer.title]"})
import twitter4j.*
import twitter4j.conf.ConfigurationBuilder


def CONSUMER_KEY="not_configured"
def CONSUMER_SECRET="not_configured"
def ACCESS_TOKEN_KEY="not_configured"
def ACCESS_TOKEN_SECRET="not_configured"

if (CONSUMER_KEY=="not_configured" || CONSUMER_SECRET=="not_configured" || ACCESS_TOKEN_KEY=="not_configured"  || ACCESS_TOKEN_SECRET=="not_configured"){
	ui.errorMessage("You need to provide valid keys to use this script!")
	return
}

ConfigurationBuilder cb = new ConfigurationBuilder()
cb.setDebugEnabled(true).setOAuthConsumerKey(CONSUMER_KEY).setOAuthConsumerSecret(CONSUMER_SECRET).setOAuthAccessToken(ACCESS_TOKEN_KEY).setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
TwitterFactory tf = new TwitterFactory(cb.build())
Twitter twitter = tf.getInstance()
ResponseList<User> users = twitter.searchUsers(node.getPlainText(),1)
if (users.size()==0){
	ui.informationMessage("Search didn't return any result.")
}
else {
	for (User user: users){
		def child = node.createChild()
    	child.setText("@"+user.getScreenName()+","+user.getName())
    	child.link.text='https://twitter.com/'+user.getScreenName()
	}
}



