<?php


namespace AppBundle\Entity;


class Team
{
    protected $id = null;

    protected $teamname;

    protected $password;

    protected $members;

    /**
     * @return mixed
     */
    public function getTeamname()
    {
        return $this->teamname;
    }

    /**
     * @param mixed $teamname
     * @return Team
     */
    public function setTeamname($teamname)
    {
        $this->teamname = $teamname;
        return $this;
    }

    /**
     * @return mixed
     */
    public function getPassword()
    {
        return $this->password;
    }

    /**
     * @param mixed $password
     * @return Team
     */
    public function setPassword($password)
    {
        $this->password = $password;
        return $this;
    }

    /**
     * @return mixed
     */
    public function getMembers()
    {
        return $this->members;
    }

    /**
     * @param mixed $members
     * @return Team
     */
    public function setMembers($members)
    {
        $this->members = $members;
        return $this;
    }

    /**
     * @param Member $member
     * @return Team
     */
    public function addMember($member)
    {
        $this->members[] = $member;
        return $this;
    }

    /**
     * @param null $id
     * @return Team
     */
    public function setId($id)
    {
        $this->id = $id;
        return $this;
    }

    /**
     * @return null
     */
    public function getId()
    {
        return $this->id;
    }

}