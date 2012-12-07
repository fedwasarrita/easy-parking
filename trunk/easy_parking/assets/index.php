<?php
ini_set("display_errors",0);error_reporting(0);
try
{
	$pdo = new PDO('mysql:host=mysql51-69.perso;dbname=courspaeandroi', 'courspaeandroi', 'LLaYq3Ia');
}
catch(Exception $e)
{
	//Definition du statut de la reponse
	$json['statut'] = array();
	$json['statut']['isSuccess'] = false;
	$json['statut']['message'] = 'KO';	
	
	echo json_encode($json);
	die();
}

if(isset($_POST['method']))
{
	//Appel à la méthode getListePlace
	if($_POST['method'] == 'getListPlace')
	{
		if(isset($_POST['long']) && isset($_POST['lat']) && isset($_POST['peri']))
		{
			$filters['Gratuit']=null;
			$filters['Handicape']=null;
			$filters['Securise']=null;
			if(isset($_POST['typePlace']))
			{
				switch($_POST['typePlace'])
				{
					case 0 : $filters['Gratuit'] = 1;break;
					case 1 : $filters['Gratuit'] = 0;break;
					default : break;
				}
			}
			if(isset($_POST['isHandicap']))
			{
				$filters['Handicape'] = (int) $_POST['isHandicap'];
			}
			if(isset($_POST['isSecured']))
			{
				$filters['Securise']= (int) $_POST['isSecured'];
			}
			getListPlace($_POST['lat'],$_POST['long'], $_POST['peri'],$filters,$pdo);
		}
	}
	//Appel à la méthode getPlace
	else if($_POST['method'] == 'getPlace')
	{
		if(isset($_POST['id']))
		{
			getPlace((int) $_POST['id'],$pdo);
		}
	}

}

/**
* 	Méthode permettant de récupérer la liste des places libres dans un périmètre donné
*	@param double $long : la longitude
*	@param double $lat : la latitude
*	@param int $peri : le perimètre
*	@param array $filters : tableau de filtres
* 	@param pdo : la connection bdd
*	@return json
*/

function getListPlace($lat,$long,$peri,$filters,$pdo)
{
	//Calcul du carré périmètre
	$d = $peri/1000;
	//$R = 6371;
	
	$latMin =  $lat - $d/111;
	$latMax = $lat + $d/111;
	
	//parce qu'on est en france et que c'est plus facile comme ça
	$longMin = $long - $d/76;
	$longMax = $long + $d/76;
		
	$query = 'SELECT p.idPlace, p.latitude, p.longitude, p.gratuit as isFree, p.handicape as isHandicap, p.securise as isSecured,';
	$query .= '	a.Adresse as adresse, a.Ville as ville, a.CodePostal as codePostal,';
	$query .= ' tp.Libelle as typePlace, ta.tarif as tarif';
	$query .= ' FROM Place p, TypePlace tp, Tarif ta, Adresse a WHERE p.adresse = a.idAdresse';
	$query .= ' AND p.idType = tp.idTypePlace';
	$query .= ' AND p.idTarif = ta.idTarif';
	
	//clause recherche des places libres
	$query .= ' AND Libre = 1';
	//Rechere dans le périmètre donné
	$query .= ' AND (p.latitude BETWEEN '.$latMin.' AND '.$latMax.')';
	$query .= ' AND (p.longitude BETWEEN '.$longMin.' AND '.$longMax.')';
	
	foreach($filters as $key => $filter)
	{
		if($filter != null)
		{
			$query .= ' AND '.$key.' = '.$filter;
		}
	}
	
	$json = array();
		
	try
	{
		$results = $pdo->query($query); 
		$i=0;	
		$places = array();
		
		//Construction de la liste de place
		while($place = $results->fetch())
		{			
			$places[$i]['idPlace']= $place['idPlace'];
			$places[$i]['latitude']= $place['latitude'];
			$places[$i]['longitude']= $place['longitude'];
			$places[$i]['adresse'] = utf8_encode($place['adresse']);
			$places[$i]['ville'] = utf8_encode($place['ville']);
			$places[$i]['codePostal'] = $place['codePostal'];
			$places[$i]['isFree']= $place['isFree'];
			$places[$i]['isHandicap']= $place['isHandicap'];
			$places[$i]['isSecured']= $place['isSecured'];
			$places[$i]['typePlace'] = utf8_encode($place['typePlace']);
			$places[$i]['tarif'] = utf8_encode($place['tarif']);
			
			$contraintes = array();
			
			$queryC = 'SELECT c.Libelle as detailContrainte, tc.Type as typeContrainte';
			$queryC .= ' FROM ContraintePlace as cp, Contraintes as c, TypeContrainte as tc';
			$queryC .= ' WHERE cp.idPlace = '.$place['idPlace'];
			$queryC .= ' AND cp.idContrainte = c.idContrainte';
			$queryC .= ' AND c.idTypeContrainte = tc.idTypeContrainte';
			
			$response = $pdo->query($queryC);
			$j=0;
			while($contrainte = $response->fetch())
			{
				$contraintes[$j]['detailContrainte'] = utf8_encode($contrainte['detailContrainte']);
				$contraintes[$j]['typeContrainte'] = utf8_encode($contrainte['typeContrainte']);
				$j++;
			}
			$response->closeCursor();
			$places[$i]['contraintes'] = $contraintes;
			$i++;
		}
		
		//Construction de la reponse
		$json['statut']['isSuccess'] = true;
		$json['statut']['message'] = 'OK';
		$json['places'] = $places;
		$results->closeCursor();
	}
	catch(Exception $e)
	{
		//Definition du statut de la reponse
		$json = array();
		$json['statut'] = array();
		$json['statut']['isSuccess'] = false;
		$json['statut']['message'] = 'KO';	
		echo json_encode($json);
		die();
	}
	
	echo json_encode($json);
	die();
}

/** 
*	Function permettant de récupérer une place
*	@param : id , l'id de la place
*	@param : la connection bdd
*/
function getPlace($id,$pdo)
{
	$query = 'SELECT p.idPlace as id, p.latitude as latitude, p.longitude as longitude, p.libre as isFree, p.handicape as isHandicap, p.securise as isSecured,';
	$query .= '	a.Adresse as adresse, a.Ville as ville, a.CodePostal as codePostal,';
	$query .= ' tp.Libelle as typePlace, ta.tarif as tarif';
	$query .= ' FROM Place p, TypePlace tp, Tarif ta, Adresse a WHERE IdPlace = '.$id;
	$query .= ' AND p.adresse = a.idAdresse';
	$query .= ' AND p.idType = tp.idTypePlace';
	$query .= ' AND p.idTarif = ta.idTarif';
	
	$json = array();
		
	try
	{
		$results = $pdo->query($query); 	
		if($ligne = $results->fetch())
		{
			$json['statut']['isSuccess'] = true;
			$json['statut']['message'] = 'OK';
			
			$json['place']['latitude'] = $ligne['latitude'];
			$json['place']['longitude'] = $ligne['longitude'];
			$json['place']['isFree'] = $ligne['isFree'];
			$json['place']['isHandicap'] = $ligne['isHandicap'];
			$json['place']['isSecured'] = $ligne['isSecured'];
			$json['place']['adresse'] = $ligne['adresse'];
			$json['place']['ville'] = $ligne['ville'];
			$json['place']['codePostal'] = $ligne['codePostal'];
			$json['place']['typePlace'] = $ligne['typePlace'];
			$json['place']['tarif'] = $ligne['tarif'];
			$contraintes = array();
			
			$queryC = 'SELECT c.Libelle as detailContrainte, tc.Type as typeContrainte';
			$queryC .= ' FROM ContraintePlace as cp, Contraintes as c, TypeContrainte as tc';
			$queryC .= ' WHERE cp.idPlace = '.$id;
			$queryC .= ' AND cp.idContrainte = c.idContrainte';
			$queryC .= ' AND c.idTypeContrainte = tc.idTypeContrainte';
			
			$response = $pdo->query($queryC);
			$j=0;
			while($contrainte = $response->fetch())
			{

				$contraintes[$j]['detailContrainte'] = utf8_encode($contrainte['detailContrainte']);
				$contraintes[$j]['typeContrainte'] = utf8_encode($contrainte['typeContrainte']);
				$j++;
			}
			$response->closeCursor();
			$json['place']['contraintes'] = $contraintes;
		}
		$results->closeCursor();
	}
	catch(Exception $e)
	{
		//Definition du statut de la reponse
		$json = array();
		$json['statut'] = array();
		$json['statut']['isSuccess'] = false;
		$json['statut']['message'] = 'KO';	
		echo json_encode($json);
		die();
	}
	
	echo json_encode($json);
	die();
}