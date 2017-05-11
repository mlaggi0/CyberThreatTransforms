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
    The script works correctly on nodes that represent an IP address.
    In case of a incorrect script call, it creates appropriate error message.
    The script uses java.net.InetAddress class to get relevant information.
    v1.0 without template method
*/

public static void main (String[] args){

	InetAddress[] domains=java.net.InetAddress.getAllByName(node.getPlainText())
    if (domains==null){
        ui.errorMessage('The node text is not an IP address!')
    }
    else {
	   for(InetAddress domain:domains){
	       def newNode=node.createChild()
	       newNode.setText(domain.getHostName())
	   }
    }
}

