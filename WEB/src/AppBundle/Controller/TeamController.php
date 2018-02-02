<?php

namespace AppBundle\Controller;

use AppBundle\Entity\Member;
use AppBundle\Entity\Team;
use AppBundle\Form\TeamLoginType;
use AppBundle\Form\TeamType;
use GuzzleHttp\Exception\ClientException;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Form;
use Symfony\Component\Form\FormBuilder;
use Symfony\Component\HttpFoundation\Request;

class TeamController extends Controller
{
    /**
     * @Route("/register", name="homepage")
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function indexAction(Request $request)
    {
        $form = $this->createForm(TeamType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            /** @var Team $data */
            $data = $form->getData();
            $apiData = [
                'Teamname' => $data->getTeamname(),
                'Password' => $data->getPassword(),
                'Members' => array_map(function ($member) {
                    return [
                        'Name' => $member->getName(),
                        'Surname' => $member->getSurname(),
                        'Mail' => $member->getMail(),
                    ];
                }, $data->getMembers()),
            ];
            try {
                $this->get('guzzle.client.api')->post('account/register', [
                    'json' => $apiData
                ]);
                $this->redirectToRoute('login');
            }
            catch(ClientException $e) {
                $data = json_decode($e->getResponse()->getBody()->getContents());
                return $this->render('team/register.html.twig', array(
                    'form' => $form->createView(),
                    'errors' => $data->Errors,
                ));
            }
        }

        return $this->render('team/register.html.twig', array(
            'form' => $form->createView(),
        ));
    }

    /**
     * @Route(name="login", path="/login")
     * @param Request $request
     * @return string
     */
    public function loginAction(Request $request) {
        $form = $this->createForm(TeamLoginType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $team = $form->getData();
            $apiData = [
                'Teamname' => $team->getTeamname(),
                'Password' => $team->getPassword(),
            ];
            try {
                $response = $this->get('guzzle.client.api')->post('account/login', [
                    'json' => $apiData
                ]);
                $data = json_decode($response->getBody()->getContents());
                $data = json_decode($data->Result);
                return $this->redirect($this->generateUrl('details', ['teamId' => $data->TeamId]));
            }
            catch(ClientException $e) {
                $data = json_decode($e->getResponse()->getBody()->getContents());
                return $this->render('team/register.html.twig', array(
                    'form' => $form->createView(),
                    'errors' => $data->Errors,
                ));
            }
        }

        return $this->render('team/login.html.twig', array(
            'form' => $form->createView(),
        ));
    }

    /**
     * @Route(name="details", path="/detail/{teamId}")
     * @param null $teamId
     * @return \Symfony\Component\HttpFoundation\RedirectResponse
     */
    public function detailAction($teamId) {
        try {
            $response = $this->get('guzzle.client.api')->get('team/details/'.$teamId, [
                'headers' => ['X-Authorization' => 'nebitno'],
            ]);
            $data = json_decode($response->getBody()->getContents());
            $data = json_decode($data->Result);
            $team = new Team();
            $team
                ->setId($teamId)
                ->setPassword($data->Password)
                ->setTeamname($data->TeamName);
            foreach($data->Members as $member) {
                $memberObj = new Member();
                $memberObj
                    ->setName($member->Name)
                    ->setSurname($member->Surname)
                    ->setMail($member->Mail);
                $team->addMember($memberObj);
            }

            return $this->render('team/detail.html.twig', array(
                'team' => $team,
            ));
        }
        catch(ClientException $e) {
            return $this->redirectToRoute('login');
        }

    }
}
